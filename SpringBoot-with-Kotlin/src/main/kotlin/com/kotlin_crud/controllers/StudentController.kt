package com.kotlin_crud.controllers

import com.kotlin_crud.model.Student
import com.kotlin_crud.services.StudentService
import com.kotlin_crud.services.twilioservice.impl.TwilioService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/students")
class StudentController(private val studentService: StudentService,private val twilioService: TwilioService) {

    private var logger:Logger = LoggerFactory.getLogger(StudentController::class.java)
    @GetMapping
    fun getAllStudents():ResponseEntity<List<Student>>{
        val students:List<Student> = studentService.getStudents()
        return ResponseEntity.ok(students)
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id:String):ResponseEntity<Any>{
        val student = studentService.getStudentById(id)
        return if(student==null) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not found with id $id")
        else return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id))
    }

    @PostMapping
    fun addStudent(@RequestBody student: Student):ResponseEntity<Student>{
        val savedStudent:Student = studentService.addStudent(student)

        val studentId = savedStudent.studentId
        val rollNumber = savedStudent.rollNo
        val registeredNumber = savedStudent.phoneNumber
        val doj = savedStudent.doj

        val message = "Registration success RegisteredId:$registeredNumber"
        twilioService.sendSingleSMS(registeredNumber,message)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent)
    }

    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id:String,@RequestBody student: Student):ResponseEntity<Student>{
        val updatedStudent:Student = studentService.updateStudent(id,student)
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent)
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id:String):ResponseEntity<Void>{
        studentService.deleteStudent(id)
        return ResponseEntity.noContent().build()
    }

}