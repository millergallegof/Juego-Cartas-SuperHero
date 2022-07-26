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
    private Integer contador = 0;

    public Mono<Juego> finalizaJuego(String idJuego) {
        return juegoRepository.findById(idJuego)
                .map(juego -> {
                    tarjetaRepository.findAll()
                            .count()
                            .subscribe(numTarjetas -> {
                                juego.getJugadores().stream()
                                        .map(jugador -> {
                                            if (!jugador.getBaraja().getTarjetas().isEmpty()) {
                                                this.contador += 1;
                                                return jugador;
                                            } else {
                                                return jugador;
                                            }
                                        });
                                var idGanador = juego.getJugadores().stream()
                                        .reduce((acum, player) -> {
                                            if (juego.getMazoJuego().size() == numTarjetas - player.getBaraja().getTarjetas().size()) {
                                                return player;
                                            } else {
                                                return acum;
                                            }
                                        }).get();
                                if (this.contador == 1) {
                                    this.jugador = idGanador;
                                }
                            });
                    juego.setGanador(this.jugador.getId());
                    return juego;
                }).retry()
                .flatMap(juegoRepository::save);
    }
}