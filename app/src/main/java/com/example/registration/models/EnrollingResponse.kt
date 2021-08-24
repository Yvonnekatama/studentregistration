package com.example.registration.models

import com.google.gson.annotations.SerializedName

data class EnrollingResponse(

    @SerializedName("course_id") var courseId:String,
    @SerializedName("enrolling_id")var enrollingId:String,
)

