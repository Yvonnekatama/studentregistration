package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.codehivereg.api.ApiInterface
import com.example.registration.Api.ApiClient
import com.example.userregistration.models.LogInRequest
import com.example.userregistration.models.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    lateinit var btnlogin: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        castViews()
    }
    fun castViews(){
        var error=false
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnlogin=findViewById(R.id.btnlogin)
        btnlogin.setOnClickListener {
            var password = etPassword.text.toString()
            if(password.isEmpty()){
                error = true
                etPassword.setError("Password is required")
            }

            var email = etEmail.text.toString()
            if(email.isEmpty()){
                error=true
                etEmail.setError("Email is required")
            }
            var loginRequest = LogInRequest(
                email=email,  password=password
            )
            var client = ApiClient.buildApiClient(ApiInterface::class.java)
            var request = client.loginStudent(loginRequest = loginRequest)

            request.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val intent = Intent(baseContext, courses::class.java)
                        startActivity(intent)
                    } else {
                        try {
                            val error = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG)
                                .show()
                        } catch (e: Exception) {
                            Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }


                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}