package co.com.sofka.usecase.juego.recibircartas;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RecibirCartasUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> recibirCartas(String id, Juego juego) {
        return juegoRepository.recibirCartas(id,juego);
    }
}
