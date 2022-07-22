package co.com.sofka.model.juego.gateways;
import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface JuegoRepository {


    Mono<Juego> save(Juego juego);
    Flux<Juego> findAll();
    Mono<Void> deleteById(String id);
    Mono<Juego> findById(String id);

}
