package co.com.sofka.model.jugador;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Jugador {
    private String identificador;
    private String puntos;
    private String baraja;

}
