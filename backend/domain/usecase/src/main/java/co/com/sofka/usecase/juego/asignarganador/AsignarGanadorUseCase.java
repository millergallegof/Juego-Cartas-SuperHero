package co.com.sofka.usecase.juego.asignarganador;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Set;

@RequiredArgsConstructor
public class AsignarGanadorUseCase {

    private final JuegoRepository juegoRepository;

    public Mono<Juego> asignarGanador(Set<Jugador> jugadores) {
        return juegoRepository.asignarGanador(jugadores);
    }

}
