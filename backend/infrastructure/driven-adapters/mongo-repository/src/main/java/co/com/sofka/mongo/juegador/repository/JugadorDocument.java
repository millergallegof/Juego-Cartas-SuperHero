package co.com.sofka.mongo.juegador.repository;

import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class JugadorDocument {
    private String identificador;
    private String puntos;
    private List<Tarjeta> baraja;
    private Boolean estado;

    public JugadorDocument(String identificador, String puntos, List<Tarjeta> baraja, Boolean estado) {
        this.identificador = identificador;
        this.puntos = puntos;
        this.baraja = baraja;
        this.estado = estado;
    }

    public JugadorDocument(String identificador, String puntos, Boolean estado) {
        this.identificador = identificador;
        this.puntos = puntos;
        this.baraja = new ArrayList<>();
        this.estado = estado;
    }
}
