package co.com.sofka.usecase.tarjeta.createtarjeta;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateTarjetaUseCase {
    private final TarjetaRepository tarjetaRepository;

    public Mono<Tarjeta> crearTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }
}
