package com.kotlin.demo.kotlin_crud.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource

@Configuration
class MessageSourceConfig {
    private val MESSAGE_SOURCE_VALID_PATH = "classpath:/validationMessage"

    @Bean
    fun messageSource(): ReloadableResourceBundleMessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasenames(MESSAGE_SOURCE_VALID_PATH)
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}