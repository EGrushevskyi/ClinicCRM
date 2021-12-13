package com.grushevskyi.clinic.repository

import com.grushevskyi.clinic.domain.Appointment

interface AppointmentRepository {
    fun createAppointment(doctorId: Long, patientId: Long, hour: Int)
    fun getAllForDoctor(doctorId: Long): List<Appointment>
    fun getAllForPatient(patientId: Long): List<Appointment>
    fun deleteAll()

    // Other CRUD methods can be added here
}
