package co.com.sofka.usecase.jugador.traerbaraja;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TraerbarajaUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> traerBaraja(String idJugador, Jugador jugador){
        return jugadorRepository.traerBaraja(idJugador, jugador);
    }
}
