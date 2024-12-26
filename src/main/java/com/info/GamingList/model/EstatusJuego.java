package com.info.GamingList.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstatusJuego {
    private int idJuego;

    /* El estatus va a ser:
    [1]: Jugando
    [2]: Finalizado
    */
    private int estatus;

    public EstatusJuego() {
        idJuego = 0;
        estatus = 0;
    }

    public EstatusJuego(int idJuego, int estatus) {
        this.idJuego = idJuego;
        this.estatus = estatus;
    }
}
