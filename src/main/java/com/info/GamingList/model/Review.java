package com.info.GamingList.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Review {
    private String nomUsuario;
    private String mensaje;
    private int calificacion;
    private int idJuego;

    public Review() {
        this.nomUsuario = "";
        this.mensaje = "";
        this.calificacion = 0;
        this.idJuego = 0;
    }

    public Review(String nomUsuario, String mensaje, int calificacion, int idJuego) {
        this.nomUsuario = nomUsuario;
        this.mensaje = mensaje;
        this.calificacion = calificacion;
        this.idJuego = idJuego;
    }


}
