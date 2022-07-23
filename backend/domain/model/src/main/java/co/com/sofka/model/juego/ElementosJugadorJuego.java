package co.com.sofka.model.juego;

import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Data;

import java.util.List;

@Data
public class ElementosJugadorJuego {
    private String idJugador;
    private List<Tarjeta> tarjetas;
}
