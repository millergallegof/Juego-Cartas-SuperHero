package co.com.sofka.usecase.jugador.actualizartarjetasjugador;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ActualizarTarjetasJugadorUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> actualizarTarjetas(String id, List<Tarjeta> tarjetas) {
        return jugadorRepository.findById(id)
                .map(jugador -> {
                    var newTarjetas = Stream.concat(tarjetas.stream(), jugador.getBaraja().getTarjetas().stream()).collect(Collectors.toList());
                    jugador.getBaraja().setTarjetas(newTarjetas);
                    return jugador;
                }).flatMap(jugadorRepository::save);
    }
}
