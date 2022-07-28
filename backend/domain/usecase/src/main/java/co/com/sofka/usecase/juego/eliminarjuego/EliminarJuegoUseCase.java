package co.com.sofka.usecase.juego.eliminarjuego;

import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EliminarJuegoUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Void> eliminarJuego(String id) {
        return juegoRepository.deleteById(id);
    }
}
