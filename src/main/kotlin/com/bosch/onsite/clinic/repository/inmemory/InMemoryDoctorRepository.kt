package com.bosch.onsite.clinic.repository.inmemory

import com.bosch.onsite.clinic.domain.Doctor
import com.bosch.onsite.clinic.repository.DoctorRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryDoctorRepository : DoctorRepository {

    val doctorList: List<Doctor> = mutableListOf(
        Doctor(1L, "surgeon"),
        Doctor(2L, "therapist")
    )

    override fun findById(doctorId: Long): Doctor? {
        return doctorList.firstOrNull { it.id == doctorId }
    }

    // Other CRUD methods can be added here
}
