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

    public RepositorioUsuario() {
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            File archivoJson = new File(archivo);
            if (archivoJson.exists()) {
                usuarios = mapper.readValue(archivoJson, new TypeReference<>() {});
            } else {
                usuarios = new ArrayList<>();
                System.out.println("Archivo no encontrado, iniciando con lista vacía.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo, iniciando con una lista vacía.");
            usuarios = new ArrayList<>();
        }
    }

    private synchronized void guardarDatos() {
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

    public void actualizar(Usuario usuarioActual, Usuario usuarioActualizado) {
        int index = usuarios.indexOf(usuarioActual);
        if (index != -1) {
            usuarios.set(index, usuarioActualizado);
            guardarDatos();
        } else {
            System.out.println("El usuario no se encontró en la lista.");
        }
    }

    public void agregar(Usuario usuario) {
        usuarios.add(usuario);
        guardarDatos();
    }
}
