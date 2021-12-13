package com.grushevskyi.clinic.repository.inmemory

import com.grushevskyi.clinic.domain.Patient
import com.grushevskyi.clinic.repository.PatientRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryPatientRepository : PatientRepository {

    val patientList: List<Patient> = mutableListOf(
        Patient(1L, "John", "Dow"),
        Patient(2L, "Jane", "Dow")
    )

    override fun findById(patientId: Long): Patient? {
        return patientList.firstOrNull { it.id == patientId }
    }

    // Other CRUD methods can be added here
}
