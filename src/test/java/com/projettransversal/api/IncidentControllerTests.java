package com.projettransversal.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.ViewModels.IncidentViewModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
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

    private int incidentTestId = 134;

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
    @DisplayName("Création d'un incident")
    public void createItem() throws Exception {
        int posX = 5;
        int posY = 6;
        float intensity = 4;
        IncidentType incidentType = IncidentType.ACCIDENT;

        IncidentViewModel incidentViewModel = new IncidentViewModel(posX, posY, intensity, incidentType);
        String incidentViewModelJson = new ObjectMapper().writeValueAsString(incidentViewModel);

        this.mockMvc.perform(post("/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .content(incidentViewModelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.intensity", is((double) intensity)))
                .andExpect(jsonPath("$.mapItem.posX", is(posX)))
                .andExpect(jsonPath("$.mapItem.posY", is(posY)))
                .andExpect(jsonPath("$.incidentType", is(incidentType.toString())));
    }

    @Test
    @Order(4)
    @DisplayName("Modification de l'intensité d'un incident")
    public void updateIntensity() throws Exception {
        int newIntensitry = 7;

        this.mockMvc.perform(post("/incident/" + this.incidentTestId + "/intensity/" + 7)
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.incidentTestId)))
                .andExpect(jsonPath("$.intensity", is((double) newIntensitry)));
    }
}
