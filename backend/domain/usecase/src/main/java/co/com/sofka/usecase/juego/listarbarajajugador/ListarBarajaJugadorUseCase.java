package co.com.sofka.usecase.juego.listarbarajajugador;
import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ListarBarajaJugadorUseCase {

    private final JuegoRepository juegoRepository;

    public Mono<Baraja> listarBarajaJugador(String id, String idJugador) {
        return juegoRepository.findById(id)
                .map(juego -> {
                    var baraja = juego.getJugadores().stream()
                            .reduce((acumulador, jugador) -> {
                                if (jugador.getId().equals(idJugador)) {
                                    return acumulador;
                                }else {
                                    return jugador;
                                }
                            }).get().getBaraja();
                    return baraja;
                });
    }
}