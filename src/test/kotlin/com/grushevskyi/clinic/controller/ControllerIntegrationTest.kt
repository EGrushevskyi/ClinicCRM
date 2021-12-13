package com.grushevskyi.clinic.controller

import com.grushevskyi.clinic.dto.MakeAppointmentDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private var objectMapper = ObjectMapper()

    @Test
    fun testCanMakeAppointment() {
        val dto = MakeAppointmentDto(1L, 1L, 1)

        val request: RequestBuilder = MockMvcRequestBuilders
            .post("/clinic/make_appointment")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(request)
            .andExpect(status().isOk)
            .andReturn()
    }

    @Test
    fun testInvalidDataReturnsStatusCode400() {
        // Doctor with this ID does not exist in DB
        val dto = MakeAppointmentDto(100L, 1L, 1)

        val request: RequestBuilder = MockMvcRequestBuilders
            .post("/clinic/make_appointment")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON)

        mockMvc.perform(request)
            .andExpect(status().isBadRequest)
            .andReturn()
    }
}
