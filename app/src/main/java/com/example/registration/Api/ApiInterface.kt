package com.example.registration.Api
import com.example.registration.models.*
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    suspend fun registerStudent(@Body registrationRequest: RegistrationRequest): retrofit2.Response<RegistrationResponse>
    @POST("/students/login")
    suspend fun loginStudent(@Body loginRequest: LogInRequest): retrofit2.Response<LogInResponse>
    @GET("/courses")
    suspend fun getCourses(@Header ("Authorization") token: String): Response<List<CoursesResponse>>
    @POST("enrolling")
    suspend fun getEnrolment(@Header("Authorization")token: String):Response<EnrollingRequest>



}
