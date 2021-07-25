package com.example.registration.models


data class LoginResponse(

    var message:String,
    var access_token:String,
    var student_id:String
)