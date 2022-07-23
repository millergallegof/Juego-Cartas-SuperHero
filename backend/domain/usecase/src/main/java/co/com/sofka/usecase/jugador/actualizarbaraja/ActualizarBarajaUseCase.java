package co.com.sofka.usecase.jugador.actualizarbaraja;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarBarajaUseCase {
    private final BarajaRepository barajaRepository;

    public Flux<Baraja> actualizarBaraja(String id, Baraja baraja) {
        return barajaRepository.update();
    }
}
