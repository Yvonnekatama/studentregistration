package com.example.codehivereg.api
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import com.example.userregistration.models.LogInRequest
import com.example.userregistration.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>
    @GET("/students/login")
    fun loginStudent(@Body loginRequest: LogInRequest): Call<LoginResponse>

}
