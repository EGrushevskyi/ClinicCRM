package com.grushevskyi.clinic.controller

import com.grushevskyi.clinic.domain.Appointment
import com.grushevskyi.clinic.dto.MakeAppointmentDto
import com.grushevskyi.clinic.service.AppointmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clinic")
class ClinicController {

    @Autowired
    private lateinit var appointmentService: AppointmentService

    @GetMapping("/doctor")
    fun displayDoctorAppointments(@RequestParam("doctorId") doctorId: Long): List<Appointment> {
        return appointmentService.getDoctorAppointments(doctorId)
    }

    @GetMapping("/patient")
    fun displayPatientAppointments(@RequestParam("patientId") patientId: Long): List<Appointment> {
        return appointmentService.getPatientAppointments(patientId)
    }

    @PostMapping("/make_appointment")
    fun makeAppointment(@RequestBody dto: MakeAppointmentDto): ResponseEntity<Any> {
        appointmentService.makeAppointment(dto)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}
