package co.com.sofka.usecase.listarbaraja;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListarBarajaUseCase {
    private final TarjetaRepository tarjetaRepository;

    public Flux<Tarjeta> listarBaraja() {
        return tarjetaRepository.listarBaraja();
    }
}
