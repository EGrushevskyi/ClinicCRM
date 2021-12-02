package com.bosch.onsite.clinic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class ClinicApplication

fun main(args: Array<String>) {
    SpringApplication.run(ClinicApplication::class.java, *args)
}