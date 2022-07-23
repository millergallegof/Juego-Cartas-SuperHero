package co.com.sofka.usecase.juego.actualizarganador;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ActualizarGanadorRondaUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> actualizarGanadorRonda(String idjuego, String idJugador, List<Tarjeta> tarjetas) {
        return juegoRepository.findById(idjuego)
                .map(juego -> {
                    var listJugadore = juego.getJugadores().stream()
                            .map(jugador -> {
                                if (jugador.getId().equals(idJugador)) {
                                    var listTarjeta = Stream.concat(jugador.getBaraja().getTarjetas().stream(), tarjetas.stream())
                                            .collect(Collectors.toList());
                                    jugador.setBaraja(new Baraja(listTarjeta));
                                    return jugador;
                                } else {
                                    return jugador;
                                }
                            }).collect(Collectors.toList());
                    juego.setJugadores(listJugadore);
                    return juego;
                }).flatMap(juegoRepository::save);

    }

}
