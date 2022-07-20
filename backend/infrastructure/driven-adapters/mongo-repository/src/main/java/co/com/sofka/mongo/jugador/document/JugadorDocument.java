package co.com.sofka.mongo.jugador.document;

import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class JugadorDocument {
    @Id
    private String identificador;

    private String nickName;

    private String puntos;

    private List<Tarjeta> baraja;

    private Boolean estado;

    public JugadorDocument(String identificador, String nickName, String puntos, List<Tarjeta> baraja, Boolean estado) {
        this.identificador = identificador;
        this.nickName = nickName;
        this.puntos = puntos;
        this.baraja = baraja;
        this.estado = estado;
    }

    public JugadorDocument(String identificador, String nickName, String puntos, Boolean estado) {
        this.identificador = identificador;
        this.nickName = nickName;
        this.puntos = puntos;
        this.estado = estado;
        this.baraja = new ArrayList<>();
    }
}
