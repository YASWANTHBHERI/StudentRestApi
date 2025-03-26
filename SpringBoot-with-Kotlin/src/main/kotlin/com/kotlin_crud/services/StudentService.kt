package com.kotlin_crud.services

import com.kotlin_crud.model.Student

interface StudentService {

    fun getStudents():List<Student>
    fun getStudentById(studentId:String):Student?
    fun addStudent(student: Student):Student
    fun updateStudent(id:String,student: Student):Student
    fun deleteStudent(id:String)

}