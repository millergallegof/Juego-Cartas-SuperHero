package co.com.sofka.model.tablero;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder(toBuilder = true)
public class Tablero {
    private String id;
    private Map<String, Tarjeta> apuesta;
    private String ganadorId;
    private Integer tiempo;


    public Tablero(String id, Map<String, Tarjeta> apuesta, String ganadorId, Integer tiempo) {
        this.id = id;
        this.apuesta = apuesta;
        this.ganadorId = ganadorId;
        this.tiempo = tiempo;
    }

    public Tablero() {
    }
}
