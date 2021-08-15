package com.example.helloworld2.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld2.viewmodel.LoginViewModel
import com.example.registration.databinding.ActivityLoginBinding
import com.example.registration.models.LogInRequest
import com.example.registration.ui.Course


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnlogin.setOnClickListener {

            var intent = Intent(baseContext, Course::class.java)
            startActivity(intent)

            var email = binding.etEmail.text.toString()
            if (email.isEmpty()){
                binding.etEmail.setError("Email required")
            }
            var password = binding.etPassword.text.toString()
            if (password.isEmpty()){
                binding.etPassword.setError("Password required")
            }
            var logInRequest = LogInRequest(
                email=email,
                password = password
            )
            loginViewModel.login(logInRequest)
        }
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.loginLIveData.observe(this,{loginResponse ->
            Toast.makeText(baseContext,loginResponse.message,Toast.LENGTH_LONG).show()

        })
        loginViewModel.loginFailedLiveData.observe(this,{error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }


}
