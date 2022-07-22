package co.com.sofka.model.juego;

import co.com.sofka.model.jugador.Jugador;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ElementosJuego {

    private List<Jugador> jugadores;
    private String idTablero;
}
