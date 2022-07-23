package co.com.sofka.usecase.jugador.apostarcarta;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ApostarCartaUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Tarjeta> apostarCarta(String idJugador, String idTarjeta) {
        return jugadorRepository.findById(idJugador)
                .map(player -> {
                  var tarjetaAEnviar =  player.getBaraja().getTarjetas().stream()
                            .reduce((acumulador, tarjeta) -> {
                                if (acumulador.getId() != idTarjeta) {
                                    return tarjeta;
                                } else {
                                    return acumulador;
                                }
                            }).get();
                       return tarjetaAEnviar;
                });
    }
}
