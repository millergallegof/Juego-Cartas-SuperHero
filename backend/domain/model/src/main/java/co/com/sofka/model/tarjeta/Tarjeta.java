package co.com.sofka.model.tarjeta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Tarjeta {
    private String id;
    private String caracteristicas;
    private String imagen;
    private Integer poderXp;
    private String descripcion;

    public Tarjeta(String id, String caracteristicas, String imagen, Integer poderXp, String descripcion) {
        this.id = id;
        this.caracteristicas = caracteristicas;
        this.imagen = imagen;
        this.poderXp = poderXp;
        this.descripcion = descripcion;
    }

    public Tarjeta() {
    }
}
