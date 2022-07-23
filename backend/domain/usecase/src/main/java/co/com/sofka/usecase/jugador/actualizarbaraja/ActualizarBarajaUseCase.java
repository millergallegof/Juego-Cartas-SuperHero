package co.com.sofka.usecase.jugador.actualizarbaraja;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarBarajaUseCase {
    private final TarjetaRepository tarjetaRepository;

    public Mono<Baraja> actualizarTarjeta(String id, Tarjeta tarjeta) {
        return tarjetaRepository.update(id, tarjeta);
    }
}
