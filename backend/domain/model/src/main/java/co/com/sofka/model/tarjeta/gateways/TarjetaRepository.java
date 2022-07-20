package co.com.sofka.model.tarjeta.gateways;

import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Mono;

public interface TarjetaRepository {
    Mono<Tarjeta> save(Tarjeta tarjeta);
}
