package co.com.sofka.mongo.baraja.document;

import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class BarajaDocument {

    @Id
    private String barajaId;

    private Set<Tarjeta> tarjetas;

    public BarajaDocument(String barajaId, Set<Tarjeta> tarjetas) {
        this.barajaId = barajaId;
        this.tarjetas = tarjetas;
    }

}
