package co.com.sofka.model.jugador.gateways;


import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface JugadorRepository {
    Mono<Tarjeta> apostarCarta(String tarjeta) ;
    Mono<Void> restirarse(String identificador);

    Flux<Tarjeta> traerBaraja(Flux<Tarjeta> baraja);

}
