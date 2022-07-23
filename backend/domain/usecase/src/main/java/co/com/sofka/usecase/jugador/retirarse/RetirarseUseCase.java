package co.com.sofka.usecase.jugador.retirarse;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RequiredArgsConstructor
public class RetirarseUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> retirarse(String jugadorId) {
        return jugadorRepository.findById(jugadorId)
                .map(jugador -> {
                    jugador.setEstado(false);
                    jugador.getBaraja().setTarjetas(new ArrayList<>());
                    return jugador;
                }).flatMap(jugadorRepository::save);
    }
}