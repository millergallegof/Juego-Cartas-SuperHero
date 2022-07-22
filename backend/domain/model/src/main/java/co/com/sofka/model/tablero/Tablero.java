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
    private String idTablero;
    private Map<JugadorId, Tarjeta> apuesta;
    private Integer tiempo;


    public Tablero(String idTablero, Map<JugadorId, Tarjeta> apuesta, Integer tiempo) {
        this.idTablero = idTablero;
        this.apuesta = apuesta;
        this.tiempo = tiempo;
    }

    public Tablero() {
    }
}
