package com.projettransversal.api;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Test du controller MapItem")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapItemControllerTests {

    private int mapItemTestId = 1;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Récupération de tous les mapItem")
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/mapItems")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(2)
    @DisplayName("Récupération d'un mapItem")
    public void getItem() throws Exception {
        this.mockMvc.perform(get("/mapItem/" + this.mapItemTestId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.mapItemTestId)))
                .andExpect(jsonPath("$.posX", greaterThan(0)))
                .andExpect(jsonPath("$.posY", greaterThan(0)))
                .andExpect(jsonPath("$.ground", notNullValue()));
    }

}
