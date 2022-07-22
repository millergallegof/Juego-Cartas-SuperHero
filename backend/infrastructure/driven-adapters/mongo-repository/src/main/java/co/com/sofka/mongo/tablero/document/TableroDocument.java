package co.com.sofka.mongo.tablero.document;

import co.com.sofka.model.jugador.JugadorId;
import co.com.sofka.mongo.tarjeta.document.TarjetaDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableroDocument {
    @Id
    private String id;
    private Map<JugadorId, TarjetaDocument> apuesta;
    private Integer tiempo;
}
