package co.com.sofka.model.baraja.gateways;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BarajaRepository {

    Mono<Baraja> save(Baraja baraja);

    Flux<Baraja> findAll();

    Flux<Baraja> update();
}
