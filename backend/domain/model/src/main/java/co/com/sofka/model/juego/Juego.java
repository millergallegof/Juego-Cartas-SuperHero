package co.com.sofka.model.juego;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Juego {

    private String id;
    private Integer ronda;
    private String ganador;
    private String tableroId;
    private List<Jugador> jugadores;

    public Juego(String id, Integer ronda, String ganador, String tableroId, List<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.ganador = ganador;
        this.tableroId = tableroId;
        this.jugadores = jugadores;
    }

    public Juego(String id, Integer ronda, String tableroId, List<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.tableroId = tableroId;
        this.jugadores = jugadores;
        this.ganador = "";
    }

    public Juego() {
    }
}
