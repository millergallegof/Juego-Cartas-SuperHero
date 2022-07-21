package co.com.sofka.usecase.juego.recibirjugadores;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class RecibirJugadoresUseCase {
    private final JuegoRepository juegoRepository;

    private final JugadorRepository jugadorRepository;

    public Flux<Jugador> recibirJugadores(List<Jugador> jugadores) {
        return juegoRepository.recibirJugadores(jugadores);

    }
}
