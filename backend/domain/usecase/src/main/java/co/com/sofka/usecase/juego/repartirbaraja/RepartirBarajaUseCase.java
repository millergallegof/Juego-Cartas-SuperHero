package co.com.sofka.usecase.juego.repartirbaraja;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RepartirBarajaUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> repartirBaraja() {
        
        return null;
    }

}
