package com.grushevskyi.clinic.repository.inmemory

import com.grushevskyi.clinic.domain.Doctor
import com.grushevskyi.clinic.repository.DoctorRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryDoctorRepository : DoctorRepository {

    private val doctorList: List<Doctor> = mutableListOf(
        Doctor(1L, "surgeon"),
        Doctor(2L, "therapist")
    )

    override fun findById(doctorId: Long): Doctor? {
        return doctorList.firstOrNull { it.id == doctorId }
    }

    // Other CRUD methods can be added here
}
