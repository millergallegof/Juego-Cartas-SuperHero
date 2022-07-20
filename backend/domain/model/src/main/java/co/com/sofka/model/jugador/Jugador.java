package co.com.sofka.model.jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Jugador {
    private String identificador;
    private String nickName;
    private String puntos;
    private List<Tarjeta> baraja;
    private Boolean estado;

    public Jugador(String identificador, String nickName, String puntos, List<Tarjeta> baraja, Boolean estado) {
        this.identificador = identificador;
        this.nickName = nickName;
        this.puntos = puntos;
        this.baraja = baraja;
        this.estado = estado;
    }

    public Jugador(String identificador, String nickName, String puntos, Boolean estado) {
        this.identificador = identificador;
        this.nickName = nickName;
        this.puntos = puntos;
        this.estado = estado;
        this.baraja = new ArrayList<>();
    }

    public Jugador() {
    }
}
