package co.com.sofka.usecase.baraja.crearbaraja;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CrearBarajaUseCase {
    private final BarajaRepository barajaRepository;

    private final TarjetaRepository tarjetaRepository;

    public Mono<Baraja> crearBaraja() {

        List<Tarjeta> barajaList = new ArrayList<>();
        Set<Tarjeta> baraja = new HashSet<>();

        var baraja2 =  tarjetaRepository.findAll()
                .map(elemen -> {
                    System.out.println(elemen);
                    barajaList.add(elemen);
                            return  elemen;
                })
                .collectList().block();
        //.subscribe(element -> barajaList.add(element));
        System.out.println(barajaList);
        Collections.shuffle(barajaList);

        // Stream
        //       .generate(new Random()::nextInt)
        //     .filter(e -> e < 9 && e > 0)
        //   .distinct()
        //  .limit(5)
        //          .forEach(System.out::println);

        // .map(randon -> {
        //      System.out.println(randon);
        //     baraja.add(tarjetas.elementAt(randon).block());
        //    return tarjetas.elementAt(randon).block();
        // });
        // tarjetas.subscribe(e-> System.out.println(e));

        System.out.println(baraja);

        return barajaRepository.save(new Baraja(baraja));
    }
}
