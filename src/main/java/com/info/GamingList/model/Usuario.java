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
    private List<Integer> idJuegos;

    public Usuario() {
        username = "";
        clave = "";
        idJuegos = new ArrayList<>();
    }

    public Usuario(String username, String clave, List<Integer> idJuegos) {
        this.username = username;
        this.clave = clave;
        this.idJuegos = idJuegos;
    }

    public void agregarIdLista(int id) {
        idJuegos.add(id);
    }

    public void eliminarIdLista(int id){
        if(idJuegos.contains(id)) {
            idJuegos.remove((Integer) id);
        }
    }
}
