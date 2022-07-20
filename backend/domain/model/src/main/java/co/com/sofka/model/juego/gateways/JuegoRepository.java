package co.com.sofka.model.juego.gateways;


import co.com.sofka.model.juego.Juego;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JuegoRepository {

    Mono<Juego> recibirJugadores(String id,Juego juego);
    Mono<Juego> recibirCartas(String id,Juego juego);
    Mono<Juego> save(Juego juego);
    Mono<Juego> enviarGanador(String id,Juego juego);

    Flux<Juego> findAll();

}
