package com.info.GamingList.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.GamingList.model.Usuario;
import com.info.GamingList.service.ServicioUsuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioUsuario servicioUsuario;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLoginExitoso() throws Exception {
        Usuario mockUsuario = new Usuario(new ArrayList<>(), "testuser", "password123");
        when(servicioUsuario.validarUsuario("testuser", "password123")).thenReturn(mockUsuario);

        Map<String, String> credenciales = new HashMap<>();
        credenciales.put("username", "testuser");
        credenciales.put("clave", "password123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credenciales)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void testLoginFallido() throws Exception {
        when(servicioUsuario.validarUsuario("testuser", "wrong")).thenReturn(null);

        Map<String, String> credenciales = new HashMap<>();
        credenciales.put("username", "testuser");
        credenciales.put("clave", "wrong");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credenciales)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testRegisterExitoso() throws Exception {
        Usuario usuario = new Usuario(new ArrayList<>(), "newuser", "pass");
        when(servicioUsuario.registrarUsuario(any(Usuario.class))).thenReturn(true);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(true));
    }
}
