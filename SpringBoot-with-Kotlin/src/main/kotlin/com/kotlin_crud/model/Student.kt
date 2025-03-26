package com.kotlin_crud.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Student (
    @Id
    val studentId:String?=null,
    var studentName:String?,
    var email:String?
)