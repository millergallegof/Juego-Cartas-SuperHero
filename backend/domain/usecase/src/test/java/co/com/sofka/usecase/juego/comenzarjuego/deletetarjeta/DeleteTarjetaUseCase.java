package co.com.sofka.usecase.juego.comenzarjuego.deletetarjeta;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteTarjetaUseCase {
    private final TarjetaRepository tarjetaRepository;

    public Mono<Void> eliminarTarjeta(String id) {
        return tarjetaRepository.deleteById(id);
    }
}
