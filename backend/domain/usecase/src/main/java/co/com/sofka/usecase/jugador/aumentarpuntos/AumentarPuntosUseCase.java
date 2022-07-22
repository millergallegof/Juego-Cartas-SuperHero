package co.com.sofka.usecase.jugador.aumentarpuntos;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AumentarPuntosUseCase {
    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> aumentarPuntos(String jugadorId) {
        return jugadorRepository.findById(jugadorId)
                .map(element -> {
                    element.setPuntos(element.getPuntos()+1);
                    return element;
                }).flatMap(jugadorRepository::save);
    }
}