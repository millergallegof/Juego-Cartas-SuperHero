package co.com.sofka.usecase.tablero.eliminarcartastablero;

import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@RequiredArgsConstructor
public class EliminarTarjetasTableroUseCase {
    private final TableroRepository tableroRepository;

    public Mono<Tablero> eliminarCartas(String idTablero) {
        return tableroRepository.findById(idTablero)
                .map(tablero -> {
                    tablero.setApuesta(new HashMap<>());
                    return tablero;
                }).flatMap(tableroRepository::save);
    }
}
