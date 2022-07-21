package co.com.sofka.model.baraja.gateways;

import co.com.sofka.model.tarjeta.Tarjeta;
import reactor.core.publisher.Flux;

public interface BarajaRepository {
    Flux<Tarjeta> enviarBaraja();
    Flux<Tarjeta> crearBaraja(Flux<Tarjeta> tarjetas);
}
