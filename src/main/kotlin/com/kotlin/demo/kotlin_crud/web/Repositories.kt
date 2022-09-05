package com.kotlin.demo.kotlin_crud.web

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{

}