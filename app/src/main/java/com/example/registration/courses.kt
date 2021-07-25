package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userregistration.Course
import com.example.userregistration.CoursesAdapter

class courses : AppCompatActivity() {
    lateinit var rvCourses: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses2)
        displayCourses()
    }
    fun displayCourses(){
        rvCourses=findViewById(R.id.rvcourses)
        var courseList= listOf(
            Course("123kotlin","Mobile Development",
                "Introduction to Android Studio",
                "John Owuor"),
            Course("123PD","Professionl Development",
                "Introduction to Django",
                "James Mwai"),
            Course("123js","FrontEnd Development",
                "Introduction to Aurellia",
                "Purity Maina"),
            Course("123Design","UI/UX Design",
                "Introduction to Adobe Design",
                "Erick Asiago")
        )
        var coursesAdapter= CoursesAdapter(courseList)
        rvCourses.layoutManager= LinearLayoutManager(baseContext)
        rvCourses.adapter=coursesAdapter

    }}