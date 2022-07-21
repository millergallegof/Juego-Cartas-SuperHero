package co.com.sofka.usecase.baraja.enviarbajara;

import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class EnviarBajaraUseCase {

    private BarajaRepository barajaRepository;

    public Flux<Tarjeta> enviarBaraja(){
   //     return barajaRepository.enviarBaraja();
        return null;
    }

}
