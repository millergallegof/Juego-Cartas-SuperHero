package co.com.sofka.usecase.jugador.retirarse;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RetirarseUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> restirarse(String idJugador, Jugador jugador){
    //    return jugadorRepository.restirarse(idJugador, jugador);

//        var jugadorId =

//        return jugadorRepository.restirarse(idJugador);
    return null;
    }
}
