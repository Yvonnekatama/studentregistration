package com.example.registration.Api
import com.example.registration.models.LogInRequest
import com.example.registration.models.LoginResponse
import com.example.registration.models.RegistrationResponse

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    suspend fun registerStudent(@Body registrationRequest: LogInRequest): retrofit2.Response<RegistrationResponse>
    @POST("/students/login")
    suspend fun loginStudent(@Body loginRequest: LogInRequest): retrofit2.Response<LoginResponse>

}
