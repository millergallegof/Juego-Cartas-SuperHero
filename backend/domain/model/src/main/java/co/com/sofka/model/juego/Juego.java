package co.com.sofka.model.juego;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder(toBuilder = true)
public class Juego {

    private String id;
    private Integer ronda;
    private ArrayList<Tarjeta> tarjetas;
    private ArrayList<Jugador> jugadores;
    private String ganador;

    public Juego(String id, Integer ronda, ArrayList<Tarjeta> tarjetas, ArrayList<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.tarjetas = tarjetas;
        this.jugadores = jugadores;
        this.ganador = "";
    }

    public Juego(String id, Integer ronda, ArrayList<Tarjeta> tarjetas, ArrayList<Jugador> jugadores, String ganador) {
        this.id = id;
        this.ronda = ronda;
        this.tarjetas = tarjetas;
        this.jugadores = jugadores;
        this.ganador = ganador;
    }

    public Juego() {
    }
}
