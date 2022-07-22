package co.com.sofka.usecase.juego.aumentaronda;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AumentaRondaUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> aumentaRonda() {
    //    return juegoRepository.findAll();
        return null;
    }
}
