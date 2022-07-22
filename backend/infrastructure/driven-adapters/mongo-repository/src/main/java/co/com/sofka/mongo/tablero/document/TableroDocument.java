package co.com.sofka.mongo.tablero.document;

import co.com.sofka.model.jugador.JugadorId;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableroDocument {
    @Id
    private String id;
    private Map<String, Tarjeta> apuesta;
    private String ganadorId;
    private Integer tiempo;
}
