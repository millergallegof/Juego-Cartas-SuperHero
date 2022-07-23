package co.com.sofka.usecase.jugador.retirarse;

import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.JugadorId;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RetirarseUseCase {

    private final JugadorRepository jugadorRepository;

    private final BarajaRepository barajaRepository;

    public Mono<Jugador> retirarse(String jugadorId) {
        return jugadorRepository.findById(jugadorId)
                .map(element -> {
                    element.setEstado(false);
                    return element;
                }).flatMap(jugadorRepository::save);
    }


}

