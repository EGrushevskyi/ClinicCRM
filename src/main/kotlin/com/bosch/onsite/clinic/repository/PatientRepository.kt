package com.bosch.onsite.clinic.repository

import com.bosch.onsite.clinic.domain.Patient

interface PatientRepository {
    fun findById(patientId: Long): Patient?

    // Other CRUD methods can be added here
}
