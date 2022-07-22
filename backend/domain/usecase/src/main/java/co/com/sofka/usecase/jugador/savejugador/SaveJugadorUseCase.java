package co.com.sofka.usecase.jugador.savejugador;


import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SaveJugadorUseCase {

        private final JugadorRepository jugadorRepository;

    public Mono<Jugador> SaveJugadorUseCase(Jugador jugador){
        return jugadorRepository.save(jugador);
    }
}
