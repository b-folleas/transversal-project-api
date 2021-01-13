package com.projettransversal.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projettransversal.api.Models.ViewModels.BarrackViewModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

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
@DisplayName("Test du controller Barrack")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BarrackControllerTests {

    private final int barrackTestId = 1;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Récupération de toutes les barracks")
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/barracks")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(2)
    @DisplayName("Récupération d'une barrack")
    public void getItem() throws Exception {
        this.mockMvc.perform(get("/barrack/" + this.barrackTestId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.barrackTestId)))
                .andExpect(jsonPath("$.name", instanceOf(String.class)))
                .andExpect(jsonPath("$.mapItem", notNullValue()));
    }

    @Test
    @Order(3)
    @DisplayName("Création d'une barrack")
    public void create() throws Exception {

        Random r = new Random();
        int posX = r.nextInt(10) + 1;
        int posY = r.nextInt(6) + 1;
        int nameNumber = r.nextInt(1000);

        BarrackViewModel barrackViewModel = new BarrackViewModel();
        barrackViewModel.setPosX(posX);
        barrackViewModel.setPosY(posY);

        barrackViewModel.setName("barrack_" + nameNumber);

        String barrackViewModelString = new ObjectMapper().writeValueAsString(barrackViewModel);

        this.mockMvc.perform(post("/barrack").contentType(MediaType.APPLICATION_JSON).content(barrackViewModelString)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("barrack_" + nameNumber)))
                .andExpect(jsonPath("$.mapItem", notNullValue()))
                .andExpect(jsonPath("$.mapItem.posX", is(posX)))
                .andExpect(jsonPath("$.mapItem.posY", is(posY)));
    }
}
