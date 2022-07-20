package co.com.sofka.usecase.jugador.guardarjugador;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GuardarJugadorUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> save(Jugador jugador){
        return jugadorRepository.save(jugador);
    }
}
