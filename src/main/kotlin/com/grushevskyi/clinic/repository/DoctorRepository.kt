package com.grushevskyi.clinic.repository

import com.grushevskyi.clinic.domain.Doctor
import com.grushevskyi.clinic.domain.Patient

interface DoctorRepository {
    fun findById(doctorId: Long): Doctor?

    // Other CRUD methods can be added here
}
