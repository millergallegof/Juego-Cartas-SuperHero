package co.com.sofka.mongo.juego.document;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class JuegoDocument {
    @Id
    private String id;
    private Integer ronda;

    private String ganador;

    private Tablero tablero;
    private Set<Jugador> jugadores;

    public JuegoDocument(String id, Integer ronda, String ganador, Tablero tablero, Set<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.ganador = ganador;
        this.tablero = tablero;
        this.jugadores = jugadores;
    }

    public JuegoDocument(String id, Integer ronda, Tablero tablero, Set<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.ganador = "";
    }
}
