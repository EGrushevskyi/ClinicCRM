package com.bosch.onsite.clinic.repository

import com.bosch.onsite.clinic.domain.Doctor
import com.bosch.onsite.clinic.domain.Patient

interface DoctorRepository {
    fun findById(doctorId: Long): Doctor?

    // Other CRUD methods can be added here
}
