package com.info.GamingList.service;

import com.info.GamingList.model.Videojuego;
import com.info.GamingList.repository.RepositorioVideojuego;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioVideojuego {

    private static final RepositorioVideojuego repositorioVideojuego = new RepositorioVideojuego();

    public List<Videojuego> obtenerVideojuegos() {
        return repositorioVideojuego.obtenerTodos();
    }

    public List<Videojuego> obtenerVideojuegosPersonal(List<Integer> ids) {
        List<Videojuego> videojuegos = new ArrayList<>();
        for (Integer id : ids) {
            videojuegos.add(obtenerVideojuego(id));
        }
        return videojuegos;
    }

    public Videojuego obtenerVideojuego(int id) {
        return repositorioVideojuego.obtenerPorId(id);
    }

    public Videojuego agregarJuego(Videojuego videojuego) throws Exception {
        Optional<Videojuego> existente = Optional.ofNullable(repositorioVideojuego.obtenerPorNombre(videojuego.getNombre()));
        if (existente.isPresent()) {
            throw new Exception("El videojuego ya existe en el catálogo.");
        }
        repositorioVideojuego.agregar(videojuego);
        return videojuego;
    }

    public boolean eliminarJuego(int id) {
        return repositorioVideojuego.eliminarPorId(id);
    }

    public int calcularIdSiguiente() {
        List<Videojuego> videojuegos = obtenerVideojuegos();
        if (videojuegos.isEmpty()) {
            return 0;
        }
        int mayorId = 0;
        for (Videojuego videojuego : videojuegos) {
            mayorId = Math.max(mayorId, videojuego.getId());
        }
        return mayorId + 1;
    }
}
