package co.com.sofka.model.jugador.gateways;


import co.com.sofka.model.jugador.Jugador;
import reactor.core.publisher.Mono;
public interface JugadorRepository {
    Mono<Jugador> apostarCarta(String carta);
    Mono<Void> restirarse(String identificador);

    

}
