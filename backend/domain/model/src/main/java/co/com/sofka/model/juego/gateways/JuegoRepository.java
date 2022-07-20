package co.com.sofka.model.juego.gateways;


import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JuegoRepository {

    Flux<Jugador> recibirJugadores();
    Flux<Tarjeta> recibirCartas();
    Mono<Tarjeta> verificarApuesta(Flux<Tarjeta> tarjetas);
    Flux<Tarjeta> envioCartas(Flux<Tarjeta> tarjetas);
    Mono<Jugador> enviarPuntaje(String id);

}
