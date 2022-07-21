package co.com.sofka.model.tablero;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder(toBuilder = true)
public class Tablero {
    private String idTablero;
    private Map<Jugador, Tarjeta> apuesta;

}
