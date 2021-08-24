package com.example.helloworld2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.Repository.LoginRepository
import com.example.registration.Repository.UserRepository
import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    var loginLIveData = MutableLiveData<LogInResponse>()
    var loginFailedLiveData = MutableLiveData<String>()
    var logRepository =LoginRepository() //create instance of the repository

    fun login(logInRequest: LogInRequest){
        viewModelScope.launch {
            var response = logRepository.loginStudent(logInRequest)
            if(response.isSuccessful){
               loginLIveData.postValue(response.body())
            }
            else{
                loginFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}