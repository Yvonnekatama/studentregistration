
    package com.example.registration.viewmodel

    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.registration.Repository.CourseRepository
    import com.example.registration.models.CoursesResponse
    import kotlinx.coroutines.launch

    class CoursesViewModel : ViewModel() {
        var courseLiveData= MutableLiveData<List<CoursesResponse>>()
        var courseFailLiveData= MutableLiveData<String>()
        var courseRepository= CourseRepository()

        fun getCourses(token: String) {
            viewModelScope.launch {
                var response=courseRepository.getCourses(token)
                if (response.isSuccessful){
                    courseLiveData.postValue(response.body())
                }
                else{
                    courseFailLiveData.postValue(response.errorBody()?.string())
                }
            }
        }
    }


