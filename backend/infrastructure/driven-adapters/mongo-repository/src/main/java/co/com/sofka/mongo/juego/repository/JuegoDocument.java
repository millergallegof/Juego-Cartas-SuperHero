package co.com.sofka.mongo.juego.repository;

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
@AllArgsConstructor
@NoArgsConstructor
public class JuegoDocument {
    @Id
    private String id;
    private Integer ronda;
    private ArrayList<Tarjeta>;
    private ArrayList<Jugador>;

}
