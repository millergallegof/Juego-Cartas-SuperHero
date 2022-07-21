package co.com.sofka.usecase.baraja.enviarbajara;

import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class EnviarBajaraUseCase {

    private final BarajaRepository barajaRepository;

    private final TarjetaRepository tarjetaRepository;

    public Flux<Tarjeta> enviarBaraja(){
    //   return barajaRepository.findAll();
        return null;
    }

}
