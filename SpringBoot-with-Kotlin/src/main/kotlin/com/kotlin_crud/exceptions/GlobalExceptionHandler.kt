package com.kotlin_crud.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException::class)
    fun handleStudentNotFoundException(ex:StudentNotFoundException): ResponseEntity<Map<String, Any>> {
        val errorResponse = mapOf(
            "message" to (ex.message?:"Student not found"),
            "status" to HttpStatus.NOT_FOUND.value()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex:Exception):ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occured: ${ex.message}")
    }
}