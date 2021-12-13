package com.grushevskyi.clinic.repository

import com.grushevskyi.clinic.domain.Patient

interface PatientRepository {
    fun findById(patientId: Long): Patient?

    // Other CRUD methods can be added here
}
