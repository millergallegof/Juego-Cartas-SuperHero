package co.com.sofka.usecase.juego.repartirbaraja;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RepartirBarajaUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> repartirBaraja(String id) {
        return juegoRepository.findById(id)
                .map(juego -> {
                    var player = juego.getJugadores().stream()
                            .map(jugador -> {
                                var cartasRandom = juego.getMazoJuego();
                                Collections.shuffle(cartasRandom);

                                var listajugadores = cartasRandom.subList(0, 5);
                                jugador.setBaraja(new Baraja(listajugadores));


                                var listActualizada = juego.getMazoJuego().stream()
                                        .filter(e -> !listajugadores.contains(e))
                                        .collect(Collectors.toList());

                                juego.setMazoJuego(listActualizada);
                                return jugador;
                            }).collect(Collectors.toList());
                    juego.setJugadores(player);
                    return juego;
                }).

                flatMap(juegoRepository::save);
    }


}
