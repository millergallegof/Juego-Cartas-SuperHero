package co.com.sofka.usecase.baraja.crearbaraja;

import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CrearBarajaUseCase {
    private BarajaRepository barajaRepository;

    public Flux<Tarjeta> crearBaraja(Flux<Tarjeta> tarjetas) {
        var tarjetasaleatorias = Stream
                .generate(new Random()::nextInt)
                .filter(e -> e < 9 && e > 0)
                .distinct()
                .limit(5)
                .map(randon -> {
                  return  tarjetas.elementAt(randon).block();
                })
                .collect(Collectors.toList());

        return barajaRepository.crearBaraja(Flux.fromIterable(tarjetasaleatorias));
    }
}
