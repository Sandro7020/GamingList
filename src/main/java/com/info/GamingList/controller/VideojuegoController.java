package com.info.GamingList.controller;

import com.info.GamingList.model.Videojuego;
import com.info.GamingList.service.ServicioVideojuego;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private static final ServicioVideojuego servicioVideojuego = new ServicioVideojuego();

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarVideojuegos() {
        try {
            List<Videojuego> videojuegos = servicioVideojuego.obtenerVideojuegos();
            return new ResponseEntity<>(videojuegos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/consultar/usuario")
    public ResponseEntity<?> consultarVideojuegosPersonal(@RequestBody List<Integer> ids) {
        try {
            List<Videojuego> videojuegos = servicioVideojuego.obtenerVideojuegosPersonal(ids);
            return new ResponseEntity<>(videojuegos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarVideojuego(@PathVariable int id) {
        try {
            Videojuego videojuego = servicioVideojuego.obtenerVideojuego(id);
            if (videojuego != null) {
                return new ResponseEntity<>(videojuego, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Videojuego no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarVideojuego(@RequestBody Videojuego videojuego) {
        try {
            Videojuego nuevoJuego = servicioVideojuego.agregarJuego(videojuego);
            return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/consultar/id")
    public ResponseEntity<?> consultarSiguienteId() {
        try {
            int id = servicioVideojuego.calcularIdSiguiente();
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
