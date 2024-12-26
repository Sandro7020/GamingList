package com.info.GamingList.controller;

import com.info.GamingList.model.EstatusJuego;
import com.info.GamingList.model.Usuario;
import com.info.GamingList.service.ServicioUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    private static final ServicioUsuario servicioUsuario = new ServicioUsuario();

    @PostMapping("/agregar/{username}/{id}")
    public ResponseEntity<?> agregarIdJuego(@PathVariable String username, @PathVariable int id) {
        try {
            servicioUsuario.actualizarListaUsuario(username, id, true);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{username}/{id}")
    public ResponseEntity<?> eliminarIdJuego(@PathVariable String username, @PathVariable int id) {
        try {
            servicioUsuario.actualizarListaUsuario(username, id, false);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/consultar/{username}/{id}")
    public ResponseEntity<?> consultarIdJuego(@PathVariable String username, @PathVariable int id) {
        try {
            boolean existe = servicioUsuario.verificarIdLista(username, id);
            return new ResponseEntity<>(existe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/consultar/{username}/videojuegos")
    public ResponseEntity<?> consultarIdVideoJuegos(@PathVariable String username) {
        try {
            List<EstatusJuego> idsJuegos = servicioUsuario.obtenerJuegos(username);
            return new ResponseEntity<>(idsJuegos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        try {
            String username = credenciales.get("username");
            String clave = credenciales.get("clave");
            Usuario user = servicioUsuario.validarUsuario(username, clave);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            boolean registroExitoso = servicioUsuario.registrarUsuario(usuario);
            return new ResponseEntity<>(registroExitoso, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarIdListas(@PathVariable int id) {
        try {
            servicioUsuario.eliminarIdListasUsuarios(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modificar/{username}/{id}")
    public ResponseEntity<?> modificarEstatus(@RequestBody int estatus, @PathVariable String username, @PathVariable int id) {
        try {
            servicioUsuario.actualizarEstatusUsuario(username, estatus, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
