package co.com.sofka.mongo.baraja.document;

import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class BarajaDocument {

    @Id
    private String barajaId;

    private List<Tarjeta> tarjetas;

    public BarajaDocument(String barajaId, List<Tarjeta> tarjetas) {
        this.barajaId = barajaId;
        this.tarjetas = tarjetas;
    }

}
