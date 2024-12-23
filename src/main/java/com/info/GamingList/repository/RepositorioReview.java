package com.info.GamingList.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.GamingList.model.Review;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioReview {
    private final String archivo = "reviews.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Review> reviews = new ArrayList<>();

    public RepositorioReview() {
        cargarDatos();
    }

    // Cargar datos del archivo JSON
    private void cargarDatos() {
        try {
            reviews = mapper.readValue(new File(archivo), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo, iniciando con una lista vacía.");
            reviews = new ArrayList<>();
        }
    }

    // Guardar datos en el archivo JSON
    private synchronized void guardarDatos() {
        try {
            mapper.writeValue(new File(archivo), reviews);
        } catch (IOException e) {
            System.out.println("No se pudo guardar en el archivo JSON.");
        }
    }

    // Métodos CRUD
    public List<Review> obtenerTodos() {
        cargarDatos();
        return reviews;
    }

    public List<Review> obtenerPorId(long id) {
        cargarDatos();
        return reviews.stream().filter(r -> r.getIdJuego() == id).toList();
    }

    public List<Review> obtenerPorUsuario(String usuario) {
        cargarDatos();
        return reviews.stream().filter(r -> r.getNomUsuario().equals(usuario)).toList();
    }

    public Review obtenerEspecifica(String usuario, int id) {
        cargarDatos();
        for(Review review : reviews){
            if(review.getIdJuego() == id && review.getNomUsuario().equals(usuario))
                return review;
        }
        return null;
    }

    public void agregar(Review review) {
        reviews.add(review);
        guardarDatos();
    }

    public boolean eliminarPorId(int id) {
        cargarDatos();
        reviews.removeIf(review -> review.getIdJuego() == id);
        guardarDatos();
        return true;
    }

    public boolean eliminarPorUsuario(String usuario, int id) {
        cargarDatos();
        reviews.removeIf(review -> review.getIdJuego() == id && review.getNomUsuario().equals(usuario));
        guardarDatos();
        return true;
    }


    public void actualizar(Review reseñaActual, Review reseñaActualizado) {
        int index = reviews.indexOf(reseñaActual);
        if (index != -1) {
            reviews.set(index, reseñaActualizado);
            guardarDatos();
        } else {
            System.out.println("La reseña no se encontró en la lista.");
        }
    }
}
