package com.info.GamingList.service;

import com.info.GamingList.model.Usuario;
import com.info.GamingList.repository.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServicioUsuario {

    private final static RepositorioUsuario repositorioUsuario = new RepositorioUsuario();

    public Usuario validarUsuario(String username, String clave) throws Exception {
        Usuario user = repositorioUsuario.obtenerPorUsername(username);
        if (user != null && Objects.equals(clave, user.getClave())) {
            return user;
        }
        throw new Exception("Error autenticando usuario");
    }

    public List<Integer> obtenerIds(String username) {
        Usuario user = repositorioUsuario.obtenerPorUsername(username);
        return new ArrayList<>(user.getIdJuegos());
    }

    public void actualizarListaUsuario(String user, int id) throws Exception {
        Usuario userActual = repositorioUsuario.obtenerPorUsername(user);
        if (userActual == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (userActual.getIdJuegos() == null) {
            userActual.setIdJuegos(new ArrayList<>());
        }

        if (!userActual.getIdJuegos().contains(id)) {
            List<Integer> nuevaListaIds = new ArrayList<>(userActual.getIdJuegos());
            Usuario userActualizado = new Usuario(userActual.getUsername(), userActual.getClave(), nuevaListaIds);

            userActualizado.agregarIdLista(id);

            repositorioUsuario.actualizar(userActual, userActualizado);
            return;
        }
        throw new Exception("Error actualizando la lista");
    }

    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> existente = Optional.ofNullable(repositorioUsuario.obtenerPorUsername(usuario.getUsername()));
        if (existente.isPresent()) {
            throw new Exception("El usuario ya existe.");
        }
        repositorioUsuario.agregar(usuario);
        return usuario;
    }
}
