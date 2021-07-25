package com.example.registration

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.codehivereg.api.ApiInterface
import com.example.registration.Api.ApiClient
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etdob: EditText
    lateinit var spNationality: Spinner
    lateinit var etEmail: EditText
    lateinit var etphone: EditText
    lateinit var etpassword: EditText
    lateinit var btnregister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castviews()
        clickregister()
    }



    fun castviews() {
        etName = findViewById(R.id.etName)
        etdob = findViewById(R.id.etdob)
        spNationality = findViewById(R.id.spNationality)
        etEmail = findViewById(R.id.etEmail)
        etphone = findViewById(R.id.etphone)
        etpassword = findViewById(R.id.etpassword)
        btnregister = findViewById(R.id.btregister)
        var nationalities = arrayOf("Kenyan", "Rwandan", "South Sudanese", "Sudanese", "Ugandan")
        var nationalitiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNationality.adapter = nationalitiesAdapter


    }

    fun clickregister() {
        btnregister.setOnClickListener {
            if (etName.text.toString().isEmpty() ||
                (etdob.text.toString() .isEmpty()) ||
                (etEmail.text.toString().isEmpty()) ||
                (etphone.text.toString().isEmpty())) {

                etName.setError("Please enter your name!")
                etdob.setError("DOB REQUIRED!")
                etEmail.setError("Input email")
                etphone.setError("Input phoneNumber")

            }


              var name=etName.text.toString()
                var nationality = spNationality.selectedItem.toString()
                var password = etpassword.text.toString()
                var phone = etphone.text.toString()
                var email = etEmail.text.toString()
                var dob=etdob.text.toString()

                var registrationRequest = RegistrationRequest(
                    name=name, phoneNumber=phone, email=email, nationality=nationality.uppercase(),
                    dateOfBirth=dob, password=password
                )
                val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
                var request = retrofit.registerStudent(registrationRequest)
                request.enqueue(object : Callback<RegistrationResponse?> {
                    override fun onResponse(
                        call: Call<RegistrationResponse?>,
                        response: Response<RegistrationResponse?>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                baseContext,
                                "Registration Successful",
                                Toast.LENGTH_LONG
                            ).show()
                            var intent=Intent(baseContext,Login::class.java)
                            startActivity(intent)

                        }else{
                            try {
                                val error = JSONObject(response.errorBody()!!.string())
                                Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG)
                                    .show()
                            } catch (e: Exception) {
                                Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
                            }

                        }
                    }

                    override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                            Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()
                    }
                })



        }
    }
}
