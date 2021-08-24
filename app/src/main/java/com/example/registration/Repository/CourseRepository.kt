package com.example.registration.Repository

import com.example.registration.Api.ApiClient
import com.example.registration.Api.ApiInterface
import com.example.registration.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CourseRepository {
    var apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun fetchCourse(accessToken:String): Response<List<CoursesResponse>> =
        withContext(Dispatchers.IO) {//coroutine has been launched
            var response = apiInterface.getCourses(accessToken)
            return@withContext response
        }

}