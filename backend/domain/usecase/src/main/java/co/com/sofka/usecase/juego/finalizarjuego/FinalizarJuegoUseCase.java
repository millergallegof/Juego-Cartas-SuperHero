package co.com.sofka.usecase.juego.finalizarjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
public class FinalizarJuegoUseCase {
    private final JuegoRepository juegoRepository;
    private final TarjetaRepository tarjetaRepository;
    private Jugador jugador;
    private Integer contador;

    public Mono<Juego> finalizaJuego(String idJuego) {
        return juegoRepository.findById(idJuego)
                .publishOn(Schedulers.boundedElastic())
                .map(juego -> {
                    tarjetaRepository.findAll()
                            .count()
                            .subscribe(numTarjetas -> {
                                var idGanador = juego.getJugadores().stream()
                                        .map(player1 -> {
                                            if (player1.getBaraja().getTarjetas().size() > 0) {
                                                this.contador += 1;
                                                return player1;
                                            } else {
                                                return player1;
                                            }
                                        })
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
                                System.out.println(this.contador);
                            });
                    System.out.println(this.contador);
                    if (this.contador == 1) {
                        juego.setGanador(this.jugador.getId());
                    }
                    return juego;
                }).retry(2)
                .flatMap(juegoRepository::save);
    }
}