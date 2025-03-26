package com.kotlin_crud.services.impl

import com.kotlin_crud.exceptions.StudentNotFoundException
import com.kotlin_crud.model.Student
import com.kotlin_crud.repositories.StudentRepo
import com.kotlin_crud.services.StudentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StudentServiceImpl(private val studentRepo:StudentRepo):StudentService {

    private val logger:Logger = LoggerFactory.getLogger(StudentServiceImpl::class.java)

    override fun getStudents(): List<Student> {
        return studentRepo.findAll()
    }

    override fun getStudentById(studentId:String): Student? {
        return studentRepo.findById(studentId)
            .orElseThrow { StudentNotFoundException("student not exists with id $studentId") }
    }

    override fun addStudent(student: Student):Student {
        val id:String = UUID.randomUUID().toString()
        val newStudent = student.copy(studentId = id)
        return studentRepo.save(newStudent)
    }

    override fun updateStudent(id: String, student: Student):Student {
        logger.info("student {}",student)
        val existingStudent = studentRepo.findById(id)
            .orElseThrow{StudentNotFoundException("student not exists with id $id")}

        val updatedStudent = existingStudent.copy(
            studentId = existingStudent.studentId,
            studentName = student.studentName?:existingStudent.studentName,
            email = student.email?:existingStudent.email

        )
        logger.info("updated student {}",student)
       return studentRepo.save(updatedStudent)
    }

    override fun deleteStudent(id: String) {
        studentRepo.deleteById(id)
    }
}