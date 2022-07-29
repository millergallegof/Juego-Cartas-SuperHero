package co.com.sofka.usecase.jugador.obtenerpuntosjugador;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ObtenerPuntosJugadorUseCase {
    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> obtenerPuntosJugador(String id) {
        return jugadorRepository.findById(id);
    }

}
