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
    private String puntos;
    private List<Tarjeta> baraja;
    private Boolean estado;

    private String nickName;

    public JugadorDocument(String identificador, String puntos, List<Tarjeta> baraja, Boolean estado,String nickName) {
        this.identificador = identificador;
        this.puntos = puntos;
        this.baraja = baraja;
        this.estado = estado;
        this.nickName = nickName;
    }

    public JugadorDocument(String identificador, String puntos, Boolean estado,String nickName) {
        this.identificador = identificador;
        this.puntos = puntos;
        this.baraja = new ArrayList<>();
        this.estado = estado;
        this.nickName = nickName;
    }
}
