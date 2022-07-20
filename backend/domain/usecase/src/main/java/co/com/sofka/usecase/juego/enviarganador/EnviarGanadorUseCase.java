package co.com.sofka.usecase.juego.enviarganador;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EnviarGanadorUseCase {

    private final JuegoRepository juegoRepository;

    public Mono<Juego> enviarGanador(String id, Juego juego) {
        return juegoRepository.enviarGanador(id,juego);
    }

}
