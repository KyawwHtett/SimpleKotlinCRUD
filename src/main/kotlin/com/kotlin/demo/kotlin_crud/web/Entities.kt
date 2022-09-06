package com.kotlin.demo.kotlin_crud.web

import org.jetbrains.annotations.NotNull
import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotEmpty
    var name: String? = null

    @NotEmpty
    var email: String? = null

    @NotEmpty
    var phone: String? = null
}