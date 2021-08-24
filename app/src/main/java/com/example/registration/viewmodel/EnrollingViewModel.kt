package com.example.registration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.models.EnrollingResponse

import com.example.registration.Repository.CourseRepository
import kotlinx.coroutines.launch

class EnrollingViewModel:ViewModel() {
    var enrollLiveData=MutableLiveData<EnrollingResponse>()
    var enrollFailLiveData=MutableLiveData<String>()
    var courseRepository= CourseRepository()

    fun getEnrolment(accessToken:String){
        viewModelScope.launch {
            var response=courseRepository.enrolling(accessToken)
            if (response.isSuccessful){
                enrollLiveData.postValue(response.body())
            }
            else{
                enrollFailLiveData.postValue(response.errorBody()?.toString())
            }
        }
    }
}