package co.com.sofka.usecase.tablero.elegirGanador;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ElegirGanadorUseCase {
    private final TableroRepository tableroRepository;

    Mono<Jugador> elegirGanador() {
        return tableroRepository.elegirGanador();
    }
}
