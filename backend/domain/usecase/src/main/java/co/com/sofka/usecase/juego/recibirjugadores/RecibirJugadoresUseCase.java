package co.com.sofka.usecase.juego.recibirjugadores;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RecibirJugadoresUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> recibirJugadores(String id, Juego juego) {
        return juegoRepository.recibirJugadores(id,juego);
    }
}
