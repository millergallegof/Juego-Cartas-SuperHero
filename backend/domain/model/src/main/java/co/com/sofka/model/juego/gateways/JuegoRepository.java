package co.com.sofka.model.juego.gateways;


import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JuegoRepository {

    Mono<Juego> recibirJugadores(String id,Juego juego);
    Mono<Juego> recibirCartas(String id,Juego juego);
    Mono<Juego> save(Juego juego);
    Mono<Juego> enviarGanador(String id,Juego juego);

}
