package com.info.GamingList.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstatusJuego {
    private int idJuego;

    /* El estatus va a ser:
    [0]: Predeterminado
    [1]: No jugado
    [2]: Quiero jugar
    [3]: Jugando
    [4]: Completado
    [5]: Completado 100%
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
