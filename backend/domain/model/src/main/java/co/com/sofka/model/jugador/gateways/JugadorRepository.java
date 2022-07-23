package co.com.sofka.model.jugador.gateways;


import co.com.sofka.model.jugador.Jugador;
import reactor.core.publisher.Mono;

public interface JugadorRepository {
    Mono<Jugador> findById(String jugadorId);
    Mono<Jugador> save(Jugador jugador);

}
