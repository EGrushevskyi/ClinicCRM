package com.bosch.onsite.clinic.controller

import com.bosch.onsite.clinic.dto.CustomError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@ControllerAdvice
class ExceptionHandler {
    private val zoneId = ZoneId.of("Europe/Berlin")

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val customError = CustomError(ex.localizedMessage, ZonedDateTime.ofInstant(Instant.now(), zoneId))
        return ResponseEntity(customError, status)
    }
}
