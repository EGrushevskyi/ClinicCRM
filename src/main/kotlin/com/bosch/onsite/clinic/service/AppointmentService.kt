package com.bosch.onsite.clinic.service

import com.bosch.onsite.clinic.domain.Appointment
import com.bosch.onsite.clinic.dto.MakeAppointmentDto
import com.bosch.onsite.clinic.repository.AppointmentRepository
import com.bosch.onsite.clinic.repository.DoctorRepository
import com.bosch.onsite.clinic.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppointmentService {

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    @Autowired
    private lateinit var doctorRepository: DoctorRepository

    @Autowired
    private lateinit var patientRepository: PatientRepository

    fun getDoctorAppointments(doctorId: Long): List<Appointment> {
        return appointmentRepository.getAllForDoctor(doctorId)
    }

    fun getPatientAppointments(patientId: Long): List<Appointment> {
        return appointmentRepository.getAllForPatient(patientId)
    }

    fun makeAppointment(dto: MakeAppointmentDto) {
        val doctorId = dto.doctorId
        val patientId = dto.patientId
        val hour = dto.hour

        if (null == doctorRepository.findById(doctorId))
            throw IllegalArgumentException("Doctor with id $doctorId not found")

        if (null == patientRepository.findById(patientId))
            throw IllegalArgumentException("Patient with id $patientId not found")

        if (!timeAvailableForDoctor(doctorId, hour))
            throw IllegalArgumentException("$doctorId is already booked at $hour")

        if (!timeAvailableForPatient(patientId, hour))
            throw IllegalArgumentException("$patientId already has an appointment at $hour")

        if(!appointmentTimeValid(hour))
            throw IllegalArgumentException("$hour is not a valid hour")

        appointmentRepository.createAppointment(doctorId, patientId, hour)
    }

    private fun appointmentTimeValid(hour: Int): Boolean {
        if( (hour >= 0) && (hour < 24) ) return true
        return false
    }

    private fun timeAvailableForDoctor(doctorId: Long, hour: Int): Boolean {
        return !appointmentRepository.getAllForDoctor(doctorId).map { it.hour }.contains(hour)
    }

    private fun timeAvailableForPatient(patientId: Long, hour: Int): Boolean {
        return !appointmentRepository.getAllForPatient(patientId).map { it.hour }.contains(hour)
    }
}
