package co.com.sofka.mongo.juego.repository;

import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JuegoDocument {

    private String id;
    private ArrayList<Tarjeta>;
    private ArrayList<Jugador>;

}
