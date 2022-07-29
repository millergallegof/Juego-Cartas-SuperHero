package co.com.sofka.usecase.jugador.obtenertarjetaapostada;

import co.com.sofka.model.jugador.gateways.JugadorRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ObtenerTarjetaApostadaJugadorUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Tarjeta> TraerTarjetaApostadaJugador(String idJugador, String idTarjeta) {
        return jugadorRepository.findById(idJugador)
                .map(player -> {
                    var tarjetaApostar = player.getBaraja().getTarjetas()
                            .stream()
                            .reduce((acumulador, tarjeta) -> {
                                if (tarjeta.getId().equals(idTarjeta)) {
                                    return tarjeta;
                                }else {
                                    return acumulador;
                                }
                            }).get();
                 return tarjetaApostar;
                });
    }
}