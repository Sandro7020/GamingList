package com.info.GamingList.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Videojuego {
    private int id; // Debería ser un hash relacionado con el nombre (sin mayus ni espacios en blanco ni caracteres especiales)
    private String nombre;
    private String descripcion;
    private String sinopsis;
    private int yearPublicacion;
    private ArrayList<String> generos;

    public Videojuego() {
        id = 0;
        nombre = "";
        descripcion = "";
        sinopsis = "";
        yearPublicacion = 0;
        generos = new ArrayList<>();
    }

    public Videojuego(int id, String nombre, String descripcion, String sinopsis, int yearPublicacion, ArrayList<String> generos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sinopsis = sinopsis;
        this.yearPublicacion = yearPublicacion;
        this.generos = generos;
    }
}
