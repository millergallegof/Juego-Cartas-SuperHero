package co.com.sofka.usecase.baraja.crearbaraja;

import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CrearBarajaUseCase {
    private BarajaRepository barajaRepository;

    public Flux<Tarjeta> crearBaraja(Flux<Tarjeta> tarjetas){
        return barajaRepository.crearBaraja(tarjetas);
    }
}
