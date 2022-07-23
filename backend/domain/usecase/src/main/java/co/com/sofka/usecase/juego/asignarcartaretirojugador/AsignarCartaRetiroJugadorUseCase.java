package co.com.sofka.usecase.juego.asignarcartaretirojugador;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class AsignarCartaRetiroJugadorUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> asignarCartasMazo(String id, String idJugador) {
        return juegoRepository.findById(id)
                .map(juego -> {
                    var jugadorRetirado = juego.getJugadores().stream()
                            .map(e -> {
                                if (e.getId().equals(idJugador)) {
                                    e.setEstado(false);
                                    return e;
                                } else {
                                    return e;
                                }
                            })
                            .reduce((acum, jugador) -> {
                                if (Boolean.FALSE.equals(jugador.getEstado())) {
                                    return jugador;
                                } else {
                                    return acum;
                                }
                            }).get();
                    var jugadoresActualizados = juego.getJugadores().stream()
                            .map(element -> {
                                if (element.getId().equals(idJugador)) {
                                    element.setBaraja(new Baraja(new ArrayList<>()));
                                    return element;
                                } else {
                                    return element;
                                }
                            }).collect(Collectors.toList());
                    var tarjetasActualizadas = Stream.concat(jugadorRetirado.getBaraja().getTarjetas().stream(), juego.getMazoJuego().stream()).collect(Collectors.toList());
                    juego.setMazoJuego(tarjetasActualizadas);
                    juego.setJugadores(jugadoresActualizados);
                    return juego;
                }).flatMap(juegoRepository::save);
    }
}
