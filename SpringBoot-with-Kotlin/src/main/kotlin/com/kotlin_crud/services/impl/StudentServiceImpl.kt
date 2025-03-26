package com.kotlin_crud.services.impl

import com.kotlin_crud.exceptions.StudentNotFoundException
import com.kotlin_crud.model.Student
import com.kotlin_crud.repositories.StudentRepo
import com.kotlin_crud.services.StudentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
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
        val rollNo = UUID.randomUUID().toString()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateOfJoining = dateFormat.format(Date())
        logger.info("joining date created {}",dateOfJoining)
        val joiningTime = Instant.now().epochSecond.toString()
        logger.info("joining time in seconds: {}",joiningTime)
        val newStudent = student.copy(studentId = id,rollNo=rollNo, doj = dateOfJoining, joiningTime = joiningTime)
        return studentRepo.save(newStudent)
    }

    override fun updateStudent(id: String, student: Student):Student {
        logger.info("student {}",student)
        val existingStudent = studentRepo.findById(id)
            .orElseThrow{StudentNotFoundException("student not exists with id $id")}

        val updatedStudent = existingStudent.copy(
            studentId = existingStudent.studentId,
            studentName = student.studentName?:existingStudent.studentName,
            rollNo = existingStudent.rollNo,
            joinedClass = student.joinedClass?:existingStudent.joinedClass,
            dob = student.dob?:existingStudent.dob,
            doj = student.doj?:existingStudent.doj,
            joiningTime = existingStudent.joiningTime,
            email = student.email?:existingStudent.email
        )
        logger.info("updated student {}",student)
       return studentRepo.save(updatedStudent)
    }

    override fun deleteStudent(id: String) {
        studentRepo.deleteById(id)
    }
}