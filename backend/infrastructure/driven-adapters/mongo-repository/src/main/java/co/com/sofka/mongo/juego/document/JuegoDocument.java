package co.com.sofka.mongo.juego.document;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class JuegoDocument {
    @Id
    private String id;
    private Integer ronda;
    private String ganador;
    private String tableroId;
    private List<Jugador> jugadores;

    public JuegoDocument(String id, Integer ronda, String ganador, String tableroId, List<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.ganador = ganador;
        this.tableroId = tableroId;
        this.jugadores = jugadores;
    }

    public JuegoDocument(String id, Integer ronda, String tableroId, List<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.tableroId = tableroId;
        this.jugadores = jugadores;
        this.ganador = "";
    }
}
