package co.com.sofka.mongo.tarjeta.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaDocument {
    @Id
    private String id;
    private String caracteristicas;
    private String imagen;
    private Integer poderXp;
    private String descripcion;
}
