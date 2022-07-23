package co.com.sofka.usecase.jugador.actualizarbaraja;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ActualizarBarajaJugadorUseCase {
    private final JugadorRepository jugadorRepository;

    public Flux<Baraja> actualizarBarajaJugador(String id, Baraja baraja) {
        return null;
    }
}
