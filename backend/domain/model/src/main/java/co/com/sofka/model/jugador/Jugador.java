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

    public Jugador(String identificador, String puntos, Boolean estado, String nickName) {
        this.identificador = identificador;
        this.puntos = puntos;
        this.baraja = new ArrayList<>();
        this.estado = estado;
        this.nickName = nickName;
    }

    public Jugador(String identificador, String puntos, List<Tarjeta> baraja, Boolean estado, String nickName) {
        this.identificador = identificador;
        this.puntos = puntos;
        this.baraja = baraja;
        this.estado = estado;
        this.nickName = nickName;
    }

    public Jugador() {
    }
}
