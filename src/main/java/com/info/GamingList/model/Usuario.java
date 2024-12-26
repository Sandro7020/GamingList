package com.info.GamingList.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Usuario {
    private String username;
    private String clave;
    private List<EstatusJuego> juegos;

    public Usuario() {
        username = "";
        clave = "";
        juegos = new ArrayList<>();
    }

    public Usuario(List<EstatusJuego> juegos, String username, String clave) {
        this.username = username;
        this.clave = clave;
        this.juegos = juegos;
    }

    public Usuario(String username, String clave, List<Integer> idJuegos) {
        this.username = username;
        this.clave = clave;
        this.juegos = new ArrayList<>();
        for (Integer id : idJuegos) {
            juegos.add(new EstatusJuego(id, 1));
        }
    }

    public void agregarIdLista(int idJuego, int estatus) {
        if (!contieneJuego(idJuego)) {
            juegos.add(new EstatusJuego(idJuego, estatus));
        }
    }

    public void eliminarIdLista(int idJuego) {
        juegos.removeIf(juego -> juego.getIdJuego() == idJuego);
    }

    public void actualizarEstatusJuego(int idJuego, int nuevoEstatus) {
        for (EstatusJuego juego : juegos) {
            if (juego.getIdJuego() == idJuego) {
                juego.setEstatus(nuevoEstatus);
                return;
            }
        }
    }

    public ArrayList<Integer> obtenerIdJuegos() {
        ArrayList<Integer> idJuegos = new ArrayList<>();
        for (EstatusJuego juego : juegos) {
            idJuegos.add(juego.getIdJuego());
        }
        return idJuegos;
    }

    // Obtener el estatus de un juego
//    public Integer obtenerEstatusJuego(int idJuego) {
//        for (EstatusJuego juego : juegos) {
//            if (juego.getIdJuego() == idJuego) {
//                return juego.getEstatus();
//            }
//        }
//        return null;
//    }

    public boolean contieneJuego(int idJuego) {
        return juegos.stream().anyMatch(juego -> juego.getIdJuego() == idJuego);
    }

    public EstatusJuego obtenerJuego(int idJuego) {
        for (EstatusJuego juego : juegos) {
            if (juego.getIdJuego() == idJuego) {
                return juego;
            }
        }
        return null;
    }
}
