package co.com.sofka.model.jugador;
import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Jugador {
    private JugadorId jugadorId;
    private String nickName;
    private Integer puntos;
    private Baraja baraja;
    private Boolean estado;

    public Jugador(JugadorId jugadorId, String nickName, Integer puntos, Baraja baraja, Boolean estado) {
        this.jugadorId = jugadorId;
        this.nickName = nickName;
        this.puntos = puntos;
        this.baraja = baraja;
        this.estado = estado;
    }

    public Jugador(JugadorId jugadorId, String nickName, Integer puntos, Boolean estado) {
        this.jugadorId = jugadorId;
        this.nickName = nickName;
        this.puntos = puntos;
        this.estado = estado;
    }

    public Jugador() {
    }
}
