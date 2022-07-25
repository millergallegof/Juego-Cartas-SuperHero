package co.com.sofka.usecase.juego.actualizarbarajajugadorjuego;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ActualizarBarajaJugadorJuegoUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> actualizarBarajaJugador(String idJuego, String idJugador, Baraja baraja) {
        return juegoRepository.findById(idJuego)
                .map(juego -> {
                    var newJugadores = juego.getJugadores().stream()
                            .map(jugador -> {
                                if (jugador.getId().equals(idJugador)) {
                                    jugador.setBaraja(baraja);
                                    return jugador;
                                }else {
                                    return jugador;
                                }
                            }).collect(Collectors.toList());
                    juego.setJugadores(newJugadores);
                    return juego;
                }).flatMap(juegoRepository::save);

    }
}
