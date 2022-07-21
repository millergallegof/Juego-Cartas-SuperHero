package co.com.sofka.usecase.tablero.createtablero;

import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateTableroUseCase {
    private final TableroRepository tableroRepository;

    public Mono<Tablero> crearTarjeta(Tablero tablero) {
        return tableroRepository.save(tablero);
    }
}
