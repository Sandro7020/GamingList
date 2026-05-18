package com.info.GamingList.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.GamingList.model.Videojuego;
import com.info.GamingList.service.ServicioVideojuego;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VideojuegoController.class)
class VideojuegoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioVideojuego servicioVideojuego;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testConsultarVideojuegos() throws Exception {
        Videojuego v1 = new Videojuego(1, "Elden Ring", "RPG", "Desc", 2022, new ArrayList<>());
        when(servicioVideojuego.obtenerVideojuegos()).thenReturn(List.of(v1));

        mockMvc.perform(get("/api/videojuegos/consultar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Elden Ring"));
    }

    @Test
    void testConsultarVideojuegoPorId() throws Exception {
        Videojuego v1 = new Videojuego(1, "Elden Ring", "RPG", "Desc", 2022, new ArrayList<>());
        when(servicioVideojuego.obtenerVideojuego(1)).thenReturn(v1);

        mockMvc.perform(get("/api/videojuegos/consultar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Elden Ring"));
    }

    @Test
    void testConsultarVideojuegoNoEncontrado() throws Exception {
        when(servicioVideojuego.obtenerVideojuego(99)).thenReturn(null);

        mockMvc.perform(get("/api/videojuegos/consultar/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAgregarVideojuego() throws Exception {
        Videojuego v1 = new Videojuego(1, "New Game", "Action", "Desc", 2024, new ArrayList<>());
        when(servicioVideojuego.agregarJuego(any(Videojuego.class))).thenReturn(v1);

        mockMvc.perform(post("/api/videojuegos/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(v1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("New Game"));
    }
}
