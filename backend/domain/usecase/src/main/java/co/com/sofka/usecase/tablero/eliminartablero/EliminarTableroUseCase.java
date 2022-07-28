package co.com.sofka.usecase.tablero.eliminartablero;

import co.com.sofka.model.tablero.gateways.TableroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EliminarTableroUseCase {

    private final TableroRepository tableroRepository;

    public Mono<Void> eliminarTablero(String id) {
        return tableroRepository.deleteById(id);
    }
}
