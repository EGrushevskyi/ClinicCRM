package com.bosch.onsite.clinic.service

import com.bosch.onsite.clinic.dto.MakeAppointmentDto
import com.bosch.onsite.clinic.repository.AppointmentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AppointmentServiceTest {

    @Autowired
    private lateinit var appointmentService: AppointmentService

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    @BeforeEach
    fun clearData() {
        appointmentRepository.deleteAll()
    }

    @Test
    fun testCanCreateAppointment() {
        appointmentService.makeAppointment(MakeAppointmentDto(1L, 1L, 2))

        val forDoctor = appointmentRepository.getAllForDoctor(1L)
        assertEquals(1, forDoctor.size)

        val forPatient = appointmentRepository.getAllForPatient(1L)
        assertEquals(1, forPatient.size)

        var appointment = forPatient[0]
        assertEquals(1L, appointment.doctorId)
        assertEquals(1L, appointment.patientId)
        assertEquals(2, appointment.hour)

        appointment = forDoctor[0]
        assertEquals(1L, appointment.doctorId)
        assertEquals(1L, appointment.patientId)
        assertEquals(2, appointment.hour)
    }

    @Test
    fun testCanGetAllDoctorsAppointments() {
        appointmentRepository.createAppointment(1L, 1L, 1)

        val appointments = appointmentService.getDoctorAppointments(1L)
        assertEquals(1, appointments.size)

        val appointment = appointments[0]
        assertEquals(1L, appointment.doctorId)
        assertEquals(1L, appointment.patientId)
        assertEquals(1, appointment.hour)
    }

    @Test
    fun testCanGetAllPatientAppointments() {
        appointmentRepository.createAppointment(1L, 1L, 1)

        val appointments = appointmentService.getPatientAppointments(1L)
        assertEquals(1, appointments.size)

        val appointment = appointments[0]
        assertEquals(1L, appointment.doctorId)
        assertEquals(1L, appointment.patientId)
        assertEquals(1, appointment.hour)
    }

    @Test
    fun testAttemptToCreateAppointmentForNonExistingDoctorThrowsException() {
        assertThrows<IllegalArgumentException> {
            // Doctor with this ID does not exist in mock doctor repository
            appointmentService.makeAppointment(MakeAppointmentDto(1000L, 1L, 2))
        }
    }

    @Test
    fun testAttemptToCreateAppointmentForNonExistingPatientThrowsException() {
        assertThrows<IllegalArgumentException> {
            // Patient with this ID does not exist in mock patient repository
            appointmentService.makeAppointment(MakeAppointmentDto(1L, 1000L, 2))
        }
    }

    @Test
    fun testAppointmentConflictForPatientThrowsException() {
        appointmentService.makeAppointment(MakeAppointmentDto(2L, 1L, 1))

        assertThrows<IllegalArgumentException> {
            // Patient with this ID does not exist in mock patient repository
            appointmentService.makeAppointment(MakeAppointmentDto(1L, 1L, 1))
        }
    }

    @Test
    fun testAppointmentConflictForDoctorThrowsException() {
        appointmentService.makeAppointment(MakeAppointmentDto(1L, 1L, 1))

        assertThrows<IllegalArgumentException> {
            appointmentService.makeAppointment(MakeAppointmentDto(1L, 2L, 1))
        }
    }

    @Test
    fun testInvalidAppontmentTimeThrowsException() {
        assertThrows<IllegalArgumentException> {
            // Patient with this ID does not exist in mock patient repository
            appointmentService.makeAppointment(MakeAppointmentDto(1L, 2L, 50))
        }
    }
}
