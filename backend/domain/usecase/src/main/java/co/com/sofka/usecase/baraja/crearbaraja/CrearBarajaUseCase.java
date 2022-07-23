package co.com.sofka.usecase.baraja.crearbaraja;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.*;

@RequiredArgsConstructor
public class CrearBarajaUseCase {
    private final BarajaRepository barajaRepository;

    private final TarjetaRepository tarjetaRepository;

    public Mono<Baraja> crearBaraja() {
        return tarjetaRepository.findAll()
                .collectList()
                .map(cartas -> {
                    Collections.shuffle(cartas);
                    return cartas;
                })
                .map(cartas -> cartas.subList(0, 5))
                .map(cartas -> Baraja.builder().tarjetas(cartas).build())
                .flatMap(barajaRepository::save);
    }

}
