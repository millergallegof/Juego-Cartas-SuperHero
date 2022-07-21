package co.com.sofka.model.juego;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Juego {

    private String id;
    private Integer ronda;

    private String ganador;

    private Tablero tablero;
    private Set<Jugador> jugadores;

    public Juego(String id, Integer ronda, String ganador, Tablero tablero, Set<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.ganador = ganador;
        this.tablero = tablero;
        this.jugadores = jugadores;
    }

    public Juego(String id, Integer ronda, Tablero tablero, Set<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.ganador = "";
    }

    public Juego() {
    }
}
