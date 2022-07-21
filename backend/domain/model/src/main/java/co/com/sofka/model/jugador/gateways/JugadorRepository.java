package co.com.sofka.model.jugador.gateways;


import co.com.sofka.model.jugador.Jugador;
import reactor.core.publisher.Mono;

import java.util.List;

public interface JugadorRepository {
    Mono<Jugador> apostarCarta(String idJugador, Jugador jugador);
    Mono<Jugador> restirarse(String idJugador, Jugador jugador);

}
