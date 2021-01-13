package com.projettransversal.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projettransversal.api.DTO.DataRequestDTO;
import com.projettransversal.api.Models.IncidentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Test du controller Incident")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IncidentControllerTests {

    private int incidentTestId = 136;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Récupération de tous les incidents")
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/incidents")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(2)
    @DisplayName("Récupération d'un incident")
    public void getItem() throws Exception {
        this.mockMvc.perform(get("/incident/" + this.incidentTestId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.incidentTestId)))
                .andExpect(jsonPath("$.intensity", greaterThan((double) -1)))
                .andExpect(jsonPath("$.intensity", lessThan((double) 11)))
                .andExpect(jsonPath("$.mapItem", notNullValue()));
    }

    @Test
    @Order(3)
    @DisplayName("Récupération des données du microbit")
    public void dataFromMicrobit() throws Exception {
        DataRequestDTO dataRequestDTO = new DataRequestDTO();

        dataRequestDTO.setData("F/1,1,1/4,3,5&I/3,4,3");

        String dataString = new ObjectMapper().writeValueAsString(dataRequestDTO);

        this.mockMvc.perform(post("/incident/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dataString))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
