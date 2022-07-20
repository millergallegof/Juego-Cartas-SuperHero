package co.com.sofka.mongo.juegador.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDocument {
    private String identificador;
    private String puntos;
    private String baraja;
}
