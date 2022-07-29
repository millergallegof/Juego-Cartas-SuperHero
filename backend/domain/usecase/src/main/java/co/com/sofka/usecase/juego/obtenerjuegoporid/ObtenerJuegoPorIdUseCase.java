package co.com.sofka.usecase.juego.obtenerjuegoporid;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ObtenerJuegoPorIdUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> finById(String id) {
        return juegoRepository.findById(id);
    }
}
