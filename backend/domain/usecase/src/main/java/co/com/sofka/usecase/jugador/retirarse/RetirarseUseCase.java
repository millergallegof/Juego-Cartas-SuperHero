package co.com.sofka.usecase.jugador.retirarse;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.JugadorId;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RetirarseUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> retirarse(String jugadorId, Jugador jugador) {
        return jugadorRepository.retirarse(jugadorId,jugador);
    }

}
