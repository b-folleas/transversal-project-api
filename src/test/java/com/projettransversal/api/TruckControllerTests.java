package com.projettransversal.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Models.Ground;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Models.ViewModels.TruckViewModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Test du controller Truck")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TruckControllerTests {

    private int truckTestId = 2;
    private int incidentTestId = 136;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Récupération de tous les trucks")
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/trucks")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(2)
    @DisplayName("Récupération d'un truck")
    public void getItem() throws Exception {
        this.mockMvc.perform(get("/truck/" + this.truckTestId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.truckTestId)))
                .andExpect(jsonPath("$.matricule", instanceOf(Integer.class)))
                .andExpect(jsonPath("$.availability", instanceOf(Boolean.class)))
                .andExpect(jsonPath("$.mapItem", notNullValue()))
                .andExpect(jsonPath("$.incidents").isArray())
                .andExpect(jsonPath("$.barrack", notNullValue()));
    }

    @Test
    @Order(3)
    @DisplayName("Lier un truck à un incident")
    public void linkToIncident() throws Exception {
        this.mockMvc.perform(get("/truck/" + this.truckTestId + "/link/incident/" + incidentTestId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.truckTestId)))
                .andExpect(jsonPath("$.matricule", instanceOf(Integer.class)))
                .andExpect(jsonPath("$.availability", instanceOf(Boolean.class)))
                .andExpect(jsonPath("$.mapItem", notNullValue()))
                .andExpect(jsonPath("$.incidents").isArray())
                .andExpect(jsonPath("$.incidents", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.barrack", notNullValue()));
    }

    @Test
    @Order(4)
    @DisplayName("Faire bouger un truck")
    public void moveTruck() throws Exception {

        Random r = new Random();
        int posX = r.nextInt(10) + 1;
        int posY = r.nextInt(6) + 1;

        this.mockMvc.perform(post("/truck/" + this.truckTestId + "/posx/" + posX + "/posy/" + posY)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.truckTestId)))
                .andExpect(jsonPath("$.matricule", instanceOf(Integer.class)))
                .andExpect(jsonPath("$.availability", instanceOf(Boolean.class)))
                .andExpect(jsonPath("$.mapItem", notNullValue()))
                .andExpect(jsonPath("$.mapItem.posX", is(posX)))
                .andExpect(jsonPath("$.mapItem.posY", is(posY)))
                .andExpect(jsonPath("$.incidents").isArray())
                .andExpect(jsonPath("$.incidents", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.barrack", notNullValue()));
    }

    @Test
    @Order(5)
    @DisplayName("Création d'un truck")
    public void createTruck() throws Exception {
        Random r = new Random();
        int posX = r.nextInt(10) + 1;
        int posY = r.nextInt(6) + 1;

        int matricule = r.nextInt(1000);

        MapItem mapItem = new MapItem();
        mapItem.setId(30L);
        mapItem.setPosX(10);
        mapItem.setPosY(3);
        mapItem.setGround(Ground.BUILDING);

        Barrack barrack = new Barrack();
        barrack.setId(1L);
        barrack.setMapItem(mapItem);
        barrack.setName("barrack_0046");

        TruckViewModel truckViewModel = new TruckViewModel();
        truckViewModel.setPosX(posX);
        truckViewModel.setPosY(posY);
        truckViewModel.setMatricule(matricule);
        truckViewModel.setBarrack(barrack);

        String truckViewModelString = new ObjectMapper().writeValueAsString(truckViewModel);

        this.mockMvc.perform(post("/truck").contentType(MediaType.APPLICATION_JSON).content(truckViewModelString)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.matricule", is(matricule)))
                .andExpect(jsonPath("$.availability", is(false)))
                .andExpect(jsonPath("$.mapItem", notNullValue()))
                .andExpect(jsonPath("$.mapItem.posX", is(posX)))
                .andExpect(jsonPath("$.mapItem.posY", is(posY)))
                .andExpect(jsonPath("$.barrack", notNullValue()));
    }



}
