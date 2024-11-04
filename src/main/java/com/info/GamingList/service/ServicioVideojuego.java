package com.info.GamingList.service;

import com.info.GamingList.model.Videojuego;
import com.info.GamingList.repository.RepositorioVideojuego;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioVideojuego {

    private static final RepositorioVideojuego repositorioVideojuego = new RepositorioVideojuego();

    public List<Videojuego> obtenerVideojuegos() {
        return repositorioVideojuego.obtenerTodos();
    }

    public Videojuego agregarJuego(Videojuego videojuego) throws Exception {
        Optional<Videojuego> existente = Optional.ofNullable(repositorioVideojuego.obtenerPorNombre(videojuego.getNombre()));
        if (existente.isPresent()) {
            throw new Exception("El videojuego ya existe en el catálogo.");
        }
        repositorioVideojuego.agregar(videojuego);
        return videojuego;
    }
}
