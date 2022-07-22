package co.com.sofka.usecase.jugador.retirarse;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class RetirarseUseCase implements BiFunction<Jugador, String, Mono<Jugador>> {

    private final JugadorRepository jugadorRepository;

    @Override
    public Mono<Jugador> apply(Jugador jugador, String jugadorId) {
        return this.jugadorRepository.findById(jugadorId)
                .switchIfEmpty(Mono.error(new Exception("No se encontro")))
                .map(player -> player.toBuilder()
                        .jugadorId(player.getJugadorId())
                        .nickName(player.getNickName())
                        .puntos(player.getPuntos())
                        .baraja(player.getBaraja())
                        .estado(player.getEstado())
                        .build())
                .flatMap(this.jugadorRepository::save);
    }
}
