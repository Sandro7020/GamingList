package com.info.GamingList.service;

import com.info.GamingList.model.Usuario;
import com.info.GamingList.repository.RepositorioUsuario;
import org.springframework.stereotype.Service;

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

    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> existente = Optional.ofNullable(repositorioUsuario.obtenerPorUsername(usuario.getUsername()));
        if (existente.isPresent()) {
            throw new Exception("El usuario ya existe.");
        }
        repositorioUsuario.agregar(usuario);
        return usuario;
    }
}
