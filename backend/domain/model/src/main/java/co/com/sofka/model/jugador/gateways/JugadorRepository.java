package co.com.sofka.model.jugador.gateways;


import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.JugadorId;
import reactor.core.publisher.Mono;

import java.util.List;

public interface JugadorRepository {
    Mono<Jugador> apostarCarta(String jugadorId, Jugador jugador);
    Mono<Jugador> findById(String jugadorId);
    Mono<Jugador> save(Jugador jugador);

    Mono<Jugador> retirarse(String idJugador, Jugador jugador);
}
