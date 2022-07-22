package co.com.sofka.model.baraja;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Baraja {
    private String id;
    private List<Tarjeta> tarjetas;


    public Baraja(String id, List<Tarjeta> tarjetas) {
        this.id = id;
        this.tarjetas = tarjetas;
    }

    public Baraja(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Baraja() {}
}
