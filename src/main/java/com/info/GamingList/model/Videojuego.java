package com.info.GamingList.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Videojuego {
    private long id; // Debería ser un hash relacionado con el nombre (sin mayus ni espacios en blanco ni caracteres especiales)
    private String nombre;
    private String descripcion;
    private ArrayList<String> generos;

    public Videojuego() {
        id = 0;
        nombre = "";
        descripcion = "";
        generos = new ArrayList<>();
    }

    public Videojuego(String nombre, String descripcion, ArrayList<String> generos) {
        this.id = 0;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.generos = generos;
    }

    @Override
    public String toString() {
        return "Videojuego {" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", generos=" + generos +
                '}';
    }
}
