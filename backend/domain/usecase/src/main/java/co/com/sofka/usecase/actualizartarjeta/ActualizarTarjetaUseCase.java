package co.com.sofka.usecase.actualizartarjeta;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarTarjetaUseCase {
    private final TarjetaRepository tarjetaRepository;

    public Mono<Tarjeta> actualizarTarjeta(String id, Tarjeta tarjeta) {
        return tarjetaRepository.update(id, tarjeta);
    }
}
