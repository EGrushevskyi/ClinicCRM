package com.grushevskyi.clinic.repository.inmemory

import com.grushevskyi.clinic.domain.Appointment
import com.grushevskyi.clinic.repository.AppointmentRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryAppointmentRepository : AppointmentRepository {

    private val appointmentData: MutableList<Appointment> = mutableListOf()

    override fun createAppointment(doctorId: Long, patientId: Long, hour: Int) {
        val appointment = Appointment(doctorId, patientId, hour)

        // In a production scenario we would also add some constraints
        // on the DB when designing the table schema
        appointmentData.add(appointment)
    }

    override fun getAllForDoctor(doctorId: Long): List<Appointment> {
        return appointmentData.filter { it.doctorId == doctorId }
    }

    override fun getAllForPatient(patientId: Long): List<Appointment> {
        return appointmentData.filter { it.patientId == patientId }
    }

    override fun deleteAll() {
        appointmentData.clear()
    }

    // Other CRUD methods can be added here
}
