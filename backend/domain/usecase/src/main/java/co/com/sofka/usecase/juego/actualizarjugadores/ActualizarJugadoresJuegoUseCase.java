package co.com.sofka.usecase.juego.actualizarjugadores;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class ActualizarJugadoresJuegoUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> actualizarJugadores(String id, Jugador jugador) {
        return juegoRepository.findById(id)
                .map(juego -> {
                    System.out.println(jugador);
                    juego.getJugadores().add(jugador);
                    return juego;
                })
                .flatMap(juegoRepository::save);
    }

}
