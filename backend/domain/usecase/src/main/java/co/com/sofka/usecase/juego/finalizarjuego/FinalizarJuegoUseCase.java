package co.com.sofka.usecase.juego.finalizarjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FinalizarJuegoUseCase {
    private final JuegoRepository juegoRepository;
    private final TarjetaRepository tarjetaRepository;
    private Jugador jugador;

    public Mono<Juego> finalizaJuego(String idJuego) {
        return juegoRepository.findById(idJuego)
                .map(juego -> {
                    tarjetaRepository.findAll()
                            .count()
                            .subscribe(element -> {
                                var idGanador = juego.getJugadores().stream()
                                        .reduce((acum, jugador) -> {
                                            if (juego.getMazoJuego().size() == element - jugador.getBaraja().getTarjetas().size()) {
                                                return jugador;
                                            } else {
                                                return acum;
                                            }
                                        }).get();
                                this.jugador = idGanador;
                            });
                    juego.setGanador(this.jugador.getId());
                    return juego;
                }).retry()
                .flatMap(juegoRepository::save);
    }
}