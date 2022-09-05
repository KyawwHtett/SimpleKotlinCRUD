package com.kotlin.demo.kotlin_crud.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun init(): String {
        return ("redirect:/user/list")
    }
}