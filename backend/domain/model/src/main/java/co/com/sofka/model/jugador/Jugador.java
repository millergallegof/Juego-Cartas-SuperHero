package co.com.sofka.model.jugador;
import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Jugador {
    private String id;
    private String nickName;
    private Integer puntos;
    private Baraja baraja;
    private Boolean estado;

    public Jugador(String id, String nickName, Integer puntos, Baraja baraja, Boolean estado) {
        this.id = id;
        this.nickName = nickName;
        this.puntos = puntos;
        this.baraja = baraja;
        this.estado = estado;
    }

    public Jugador(String id, String nickName, Integer puntos, Boolean estado) {
        this.id = id;
        this.nickName = nickName;
        this.puntos = puntos;
        this.estado = estado;
    }

    public Jugador() {
    }
}
