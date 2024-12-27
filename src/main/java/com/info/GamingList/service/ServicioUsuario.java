package com.info.GamingList.service;

import com.info.GamingList.model.EstatusJuego;
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

    public Usuario validarUsuario(String username, String clave) {
        Usuario user = repositorioUsuario.obtenerPorUsername(username);
        if (user != null && Objects.equals(clave, user.getClave())) {
            return user;
        }
        return null;
    }

    public boolean verificarIdLista(String username, int id) {
        List<Integer> ids = obtenerIds(username);
        return ids.contains(id);
    }

    public List<Integer> obtenerIds(String username) {
        Usuario user = repositorioUsuario.obtenerPorUsername(username);
        return user.obtenerIdJuegos();
    }

    public List<EstatusJuego> obtenerJuegos(String username) {
        Usuario user = repositorioUsuario.obtenerPorUsername(username);
        return user.getJuegos();
    }

    public void actualizarListaUsuario(String user, int id, boolean opcion) throws Exception {
        Usuario userActual = repositorioUsuario.obtenerPorUsername(user);
        if (userActual == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (userActual.getJuegos() == null) {
            userActual.setJuegos(new ArrayList<>());
        }

        List<EstatusJuego> nuevaListaIds = userActual.getJuegos();
        Usuario userActualizado = new Usuario(nuevaListaIds, userActual.getUsername(), userActual.getClave());

        if(opcion) {
            if (!userActual.contieneJuego(id)) {
                userActualizado.agregarIdLista(id, 0);
            }
        }
        else {
            if (userActual.contieneJuego(id)) {
                userActualizado.eliminarIdLista(id);
            }
        }
        repositorioUsuario.actualizar(userActual, userActualizado);
    }

    public void actualizarEstatusUsuario(String user, int estatus, int id) throws Exception {
        Usuario userActual = repositorioUsuario.obtenerPorUsername(user);
        if (userActual == null) {
            throw new Exception("Usuario no encontrado");
        }

        Usuario userActualizado = new Usuario(userActual.getJuegos(), userActual.getUsername(), userActual.getClave());
        userActualizado.actualizarEstatusJuego(id, estatus);

        repositorioUsuario.actualizar(userActual, userActualizado);
    }

    public boolean registrarUsuario(Usuario usuario) {
        Optional<Usuario> existente = Optional.ofNullable(repositorioUsuario.obtenerPorUsername(usuario.getUsername()));
        if (existente.isPresent()) {
            return false;
        }
        repositorioUsuario.agregar(usuario);
        return true;
    }

    public void eliminarIdListasUsuarios(int id) {
        repositorioUsuario.eliminarIdDeListas(id);
    }
}
