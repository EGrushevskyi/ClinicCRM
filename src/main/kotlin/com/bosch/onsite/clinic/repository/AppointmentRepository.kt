package com.bosch.onsite.clinic.repository

import com.bosch.onsite.clinic.domain.Appointment

interface AppointmentRepository {
    fun createAppointment(doctorId: Long, patientId: Long, hour: Int)
    fun getAllForDoctor(doctorId: Long): List<Appointment>
    fun getAllForPatient(patientId: Long): List<Appointment>
    fun deleteAll()

    // Other CRUD methods can be added here
}
