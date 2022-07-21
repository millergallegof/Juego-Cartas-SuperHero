package co.com.sofka.model.tarjeta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Tarjeta {
    private String id;
    private String descripcion;
    private Integer poderXp;
    private String imagen;


    public Tarjeta(String id, String descripcion, Integer poderXp, String imagen) {
        this.id = id;
        this.descripcion = descripcion;
        this.poderXp = poderXp;
        this.imagen = imagen;
    }

    public Tarjeta() {
    }
}
