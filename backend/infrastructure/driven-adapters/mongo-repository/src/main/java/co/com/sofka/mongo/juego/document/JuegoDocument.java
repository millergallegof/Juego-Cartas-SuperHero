package co.com.sofka.mongo.juego.document;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Data
@NoArgsConstructor
public class JuegoDocument {
    @Id
    private String id;
    private Integer ronda;
    private ArrayList<Tarjeta> tarjetas;
    private ArrayList<Jugador> jugadores;
    private String ganador;

    public JuegoDocument(String id, Integer ronda, ArrayList<Tarjeta> tarjetas, ArrayList<Jugador> jugadores) {
        this.id = id;
        this.ronda = ronda;
        this.tarjetas = tarjetas;
        this.jugadores = jugadores;
        this.ganador = "";
    }

    public JuegoDocument(String id, Integer ronda, ArrayList<Tarjeta> tarjetas, ArrayList<Jugador> jugadores, String ganador) {
        this.id = id;
        this.ronda = ronda;
        this.tarjetas = tarjetas;
        this.jugadores = jugadores;
        this.ganador = ganador;
    }
}
