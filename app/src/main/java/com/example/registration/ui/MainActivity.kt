package com.example.registration.ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.registration.Api.ApiInterface
import com.example.registration.Api.ApiClient
import com.example.registration.R
import com.example.registration.databinding.ActivityMainBinding
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import com.example.registration.viewmodel.UserViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        binding.btregister.setOnClickListener {

        }
    }

    fun registerStudent() {
        val name = binding.etName.text.toString()
        if (name.isEmpty()) {
            binding.etName.setError("Name required")

            val email = binding.etEmail.text.toString()
        if (email.isEmpty()) {
            binding.etEmail.setError("Email required")

            val dateOfBirth = binding.etdob.text.toString()
            if(dateOfBirth.isEmpty()){
                binding.etdob.setError("DOB required")
            }
            val phoneNumber = binding.etphone.text.toString()
            if (phoneNumber.isEmpty()) {
                binding.etphone.setError("Phone number required")

                val password = binding.etpassword.text.toString()
                if (password.isEmpty()) {
                    binding.etpassword.setError("Email required")

                    var nationality =
                arrayListOf<String>("Kenyan", "Ugandan", "Rwandese", "South Sudanesse")
            var nationalityAdapter =
                ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationality)
            binding.spNationality.adapter = nationalityAdapter
            nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


            var registrationRequest = RegistrationRequest(
                name = name,
                email = email,
                dateOfBirth = dateOfBirth,
                phoneNumber = phoneNumber,
                password = password,
                nationality = binding.spNationality.selectedItem.toString().uppercase()
            )
            userViewModel.registerUser(registrationRequest)
            userViewModel.registrationLiveData.observe(this, { registrationResponse ->
                if (!registrationResponse.studentId.isNullOrEmpty()) {
                    Toast.makeText(baseContext, "Registration Successful", Toast.LENGTH_LONG).show()
                }
            })
            userViewModel.regFailedLiveData.observe(this, { error ->

                Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

            })

        }
    }
}