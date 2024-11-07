package com.info.GamingList.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.GamingList.model.Usuario;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioUsuario {
    private final String archivo = "usuarios.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Usuario> usuarios = new ArrayList<>();

    // Cargar datos del archivo JSON
    private void cargarDatos() {
        try {
            usuarios = mapper.readValue(new File(archivo), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo, iniciando con una lista vacía.");
            usuarios = new ArrayList<>();
        }
    }

    // Guardar datos en el archivo JSON
    private void guardarDatos() {
        try {
            mapper.writeValue(new File(archivo), usuarios);
        } catch (IOException e) {
            System.out.println("No se pudo guardar en el archivo JSON.");
        }
    }

    // Métodos CRUD
//    public Videojuego obtenerPorId(long id) {
//        cargarDatos();
//        return videojuegos.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
//    }

    public Usuario obtenerPorUsername(String nombre) {
        cargarDatos();
        return usuarios.stream().filter(u -> u.getUsername().equals(nombre)).findFirst().orElse(null);
    }

    public void agregar(Usuario usuario) {
        usuarios.add(usuario);
        guardarDatos();
    }
}
