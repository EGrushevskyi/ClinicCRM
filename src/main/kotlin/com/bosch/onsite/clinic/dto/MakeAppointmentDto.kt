package com.bosch.onsite.clinic.dto

data class MakeAppointmentDto(
    var doctorId: Long,
    var patientId: Long,
    var hour: Int
)
