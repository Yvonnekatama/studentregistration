package com.example.registration.Api
import com.example.registration.models.LogInRequest
import com.example.registration.models.LoginResponse
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>
    @POST("/students/login")
    fun loginStudent(@Body loginRequest: LogInRequest): Call<LoginResponse>

}
