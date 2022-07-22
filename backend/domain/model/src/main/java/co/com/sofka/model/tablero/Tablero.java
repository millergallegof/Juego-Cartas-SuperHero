package co.com.sofka.model.tablero;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.JugadorId;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder(toBuilder = true)
public class Tablero {
    private String id;
    private Map<String, Tarjeta> apuesta;
    private Integer tiempo;


    public Tablero(String id, Map<String, Tarjeta> apuesta, Integer tiempo) {
        this.id = id;
        this.apuesta = apuesta;
        this.tiempo = tiempo;
    }

    public Tablero() {
    }
}
