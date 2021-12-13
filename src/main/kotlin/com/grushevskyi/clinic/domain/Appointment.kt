package com.grushevskyi.clinic.domain

data class Appointment(
    val doctorId: Long,
    val patientId: Long,
    val hour: Int
)
