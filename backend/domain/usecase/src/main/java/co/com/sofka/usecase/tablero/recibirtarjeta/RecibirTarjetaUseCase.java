package co.com.sofka.usecase.tablero.recibirtarjeta;

import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
public class RecibirTarjetaUseCase {
    private final TableroRepository tableroRepository;

    public Mono<Tablero> recibirTarjetas(String id, Map<String, Tarjeta> tarjetaJugadores) {
        return tableroRepository.findById(id)
                .map(tablero -> {
                    tablero.setApuesta(tarjetaJugadores);
                    return tablero;
                })
                .flatMap(tableroRepository::save);

    }

}
