package com.example.userregistration

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Constants
import com.example.registration.R
import com.example.registration.models.EnrollingRequest
import com.example.registration.ui.Course
import com.example.registration.viewmodel.EnrollingViewModel

class CoursesAdapter(var courseList:List<Course>):RecyclerView.Adapter<CoursesViewHolder>(){
    private lateinit var enrollViewModel: EnrollingViewModel
    private lateinit var sharedPrefs: SharedPreferences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.courses_list_item,parent,false)
        return CoursesViewHolder(itemView)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        var currentCourse=courseList[position]
        holder.tvCourseName.text=currentCourse.courseName
        holder.tvDescription.text=currentCourse.description
        holder.tvTrainer.text=currentCourse.instructor
        holder.tvCode.text=currentCourse.courseCode

        holder.btnenroll.setOnClickListener {
            sharedPrefs=sharedPrefs
            var studentId=sharedPrefs.edit()
            var courseId = sharedPrefs.edit()
            var enrolmentRequest=EnrollingRequest(
                studentId = studentId.toString(),
                courseId = courseId.toString()
            )
            enrollViewModel.getEnrolment(Constants.toString())
        }

    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}
class CoursesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvCourseName=itemView.findViewById<TextView>(R.id.tvcourseName)
    var tvDescription=itemView.findViewById<TextView>(R.id.tvDescription)
    var tvTrainer=itemView.findViewById<TextView>(R.id.tvtrainer)
    var tvCode=itemView.findViewById<TextView>(R.id.textview)
    var btnenroll=itemView.findViewById<Button>(R.id.btnenroll)
}
