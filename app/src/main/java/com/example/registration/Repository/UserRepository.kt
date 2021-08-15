package com.example.registration.Repository

import com.example.registration.Api.ApiClient
import com.example.registration.Api.ApiInterface
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    var apiInterface=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerStudent(registrationRequest: RegistrationRequest):retrofit2.Response<RegistrationResponse> =
        withContext(Dispatchers.IO){//coroutine has been launched
           var response=apiInterface.registerStudent(registrationRequest)
            return@withContext response
        }
}