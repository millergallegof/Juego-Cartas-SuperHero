package co.com.sofka.model.tarjeta.gateways;

import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarjetaRepository {
    Mono<Tarjeta> save(Tarjeta tarjeta);
    Mono<Void> deleteById(String id);
    Mono<Tarjeta> update(String id, Tarjeta tarjeta);
    Flux<Tarjeta> findAll();

}
