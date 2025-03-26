package com.kotlin_crud.repositories

import com.kotlin_crud.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepo: JpaRepository<Student,String> {

}