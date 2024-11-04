package com.info.GamingList.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.info.GamingList.model.Videojuego;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioVideojuego {
    private final String archivo = "videojuegos.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Videojuego> videojuegos = new ArrayList<>();

    public RepositorioVideojuego() {
        cargarDatos();
    }

    // Cargar datos del archivo JSON
    private void cargarDatos() {
        try {
            videojuegos = mapper.readValue(new File(archivo), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo, iniciando con una lista vacía.");
            videojuegos = new ArrayList<>();
        }
    }

    // Guardar datos en el archivo JSON
    private void guardarDatos() {
        try {
            mapper.writeValue(new File(archivo), videojuegos);
        } catch (IOException e) {
            System.out.println("No se pudo guardar en el archivo JSON.");
        }
    }

    // Métodos CRUD
    public List<Videojuego> obtenerTodos() {
        cargarDatos();
        return videojuegos;
    }

    public Videojuego obtenerPorId(long id) {
        cargarDatos();
        return videojuegos.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    public Videojuego obtenerPorNombre(String nombre) {
        cargarDatos();
        return videojuegos.stream().filter(v -> v.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public void agregar(Videojuego videojuego) {
        videojuegos.add(videojuego);
        guardarDatos();
    }

    public void actualizar(Videojuego videojuego) {
        videojuegos = videojuegos.stream()
            .map(v -> v.getId() == videojuego.getId() ? videojuego : v)
            .toList();
        guardarDatos();
    }

    public void eliminar(long id) {
        videojuegos.removeIf(v -> v.getId() == id);
        guardarDatos();
    }
}
