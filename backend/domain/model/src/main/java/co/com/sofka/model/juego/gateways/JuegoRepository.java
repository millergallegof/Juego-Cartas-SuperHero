package co.com.sofka.model.juego.gateways;


import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface JuegoRepository {

    Flux<Jugador> recibirJugadores(List<Jugador> jugadores);
    Flux<Tarjeta> recibirCartas(List<Tarjeta> tarjetas);
    Mono<Juego> save(Juego juego);
    Mono<Juego> enviarGanador(String id,Juego juego);
    Flux<Juego> findAll();

}
