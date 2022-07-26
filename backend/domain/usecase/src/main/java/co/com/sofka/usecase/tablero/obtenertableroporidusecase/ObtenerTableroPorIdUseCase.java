package co.com.sofka.usecase.tablero.obtenertableroporidusecase;

import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ObtenerTableroPorIdUseCase {
    private final TableroRepository tableroRepository;

    public Mono<Tablero> obtenerTablero(String idTablero) {
        return tableroRepository.findById(idTablero);
    }
}
