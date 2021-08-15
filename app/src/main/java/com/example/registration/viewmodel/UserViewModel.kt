package com.example.registration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registration.Repository.UserRepository
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserViewModel:ViewModel() {
    var registrationLiveData=MutableLiveData<RegistrationResponse>()
    var regFailedLiveData=MutableLiveData<String>()
    var userRepository=UserRepository()

    fun registerUser(registrationRequest: RegistrationRequest){
      viewModelScope.launch{
          var response=userRepository.registerStudent(registrationRequest)
          if(response.isSuccessful){
              registrationLiveData.postValue(response.body())
          }
          else{
              regFailedLiveData.postValue(response.errorBody().toString())

          }

      }
    }
}