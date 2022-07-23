package co.com.sofka.usecase.jugador.apostarcarta;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApostarCartaUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> apostarCarta(String idJugador, String idTarjeta) {
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
                    var listarTarjetaActualizar = player.getBaraja().getTarjetas().stream()
                            .filter(element -> tarjetaAEnviar.getId().contains(element.getId()))
                            .collect(Collectors.toList());
                    System.out.println(listarTarjetaActualizar);
                    player.setBaraja(new Baraja(listarTarjetaActualizar));
                    return player;
                })
                .flatMap(jugadorRepository::save);
    }
}
