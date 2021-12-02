package com.bosch.onsite.clinic.repository.inmemory

import com.bosch.onsite.clinic.repository.AppointmentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AppointmentRepositoryTest {

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    @BeforeEach
    fun clearTestData() {
        appointmentRepository.deleteAll()
    }

    @Test
    fun testCanCreateAndGetAllDoctorsAppointments() {
        appointmentRepository.createAppointment(1L, 1L, 1)

        val appointments = appointmentRepository.getAllForDoctor(1L)
        assertEquals(1, appointments.size)

        val appointment = appointments[0]
        assertEquals(1L, appointment.doctorId)
        assertEquals(1L, appointment.patientId)
        assertEquals(1, appointment.hour)
    }

    @Test
    fun testCanCreateAndGetAllPatientAppointments() {
        appointmentRepository.createAppointment(1L, 1L, 1)

        val appointments = appointmentRepository.getAllForPatient(1L)
        assertEquals(1, appointments.size)

        val appointment = appointments[0]
        assertEquals(1L, appointment.doctorId)
        assertEquals(1L, appointment.patientId)
        assertEquals(1, appointment.hour)
    }
}
