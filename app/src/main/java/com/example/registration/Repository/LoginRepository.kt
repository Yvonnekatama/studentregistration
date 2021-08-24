package com.example.registration.Repository

import com.example.registration.Api.ApiClient
import com.example.registration.Api.ApiInterface
import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository {
    var apiInterface= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginStudent(logInRequest: LogInRequest):retrofit2.Response<LogInResponse> =
        withContext(Dispatchers.IO){//coroutine has been launched
            var response=apiInterface.loginStudent(logInRequest)
            return@withContext response
        }
}