package com.kotlin_crud.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Student (
    @Id
    val studentId:String?=null,
    var studentName:String?,
    var rollNo:String?,
    var joinedClass:Int?,
    var dob:String?,
    var doj:String?,
    var joiningTime:String?,
    var email:String,
    var phoneNumber:String
)