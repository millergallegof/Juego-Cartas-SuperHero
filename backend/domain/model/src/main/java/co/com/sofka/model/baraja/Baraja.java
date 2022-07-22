package co.com.sofka.model.baraja;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Baraja {
    private String barajaId;
    private List<Tarjeta> tarjetas;


    public Baraja(String barajaId, List<Tarjeta> tarjetas) {
        this.barajaId = barajaId;
        this.tarjetas = tarjetas;
    }

    public Baraja(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Baraja() {}
}
