package com.gahgsp.poketlin.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
@EnableCaching
class AppConfig {
    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()
}