package com.info.GamingList.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.GamingList.model.Review;
import com.info.GamingList.service.ServicioReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioReview servicioReview;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testConsultarReviews() throws Exception {
        Review r1 = new Review();
        r1.setIdJuego(1);
        r1.setMensaje("Excelente");
        
        when(servicioReview.obtenerReviewsId(1)).thenReturn(List.of(r1));

        mockMvc.perform(get("/api/reviews/consultar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].mensaje").value("Excelente"));
    }

    @Test
    void testConsultarCalificacion() throws Exception {
        when(servicioReview.calcularCalificacionJuego(1)).thenReturn(4.5678f);

        mockMvc.perform(get("/api/reviews/consultar/calificacion/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(4.57)); // Debería estar redondeado por el controlador
    }
}
