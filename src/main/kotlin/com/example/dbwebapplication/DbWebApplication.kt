package com.example.dbwebapplication

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class DbWebApplication

fun main(args: Array<String>) {
    runApplication<DbWebApplication>(*args)
}