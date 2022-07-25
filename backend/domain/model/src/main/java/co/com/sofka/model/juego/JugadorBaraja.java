package co.com.sofka.model.juego;

import co.com.sofka.model.baraja.Baraja;
import lombok.Data;

@Data
public class JugadorBaraja {
    private String idJugador;
    private Baraja baraja;
}
