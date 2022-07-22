package co.com.sofka.model.jugador.gateways;


import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.JugadorId;
import reactor.core.publisher.Mono;

public interface JugadorRepository {
    Mono<Jugador> apostarCarta(JugadorId jugadorId, Jugador jugador);
    Mono<Jugador> findById(String jugadorId);
    Mono<Jugador> save(Jugador jugador);
}
