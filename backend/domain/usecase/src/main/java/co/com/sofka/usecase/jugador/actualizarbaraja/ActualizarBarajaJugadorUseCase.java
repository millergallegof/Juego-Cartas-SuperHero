package co.com.sofka.usecase.jugador.actualizarbaraja;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActualizarBarajaJugadorUseCase {
    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> actualizarBarajaJugador(String id, Baraja baraja) {
       return jugadorRepository.findById(id)
                .map(jugador -> {
                    jugador.setBaraja(baraja);
                    return jugador;
                }).flatMap(jugadorRepository::save);
    }
}
