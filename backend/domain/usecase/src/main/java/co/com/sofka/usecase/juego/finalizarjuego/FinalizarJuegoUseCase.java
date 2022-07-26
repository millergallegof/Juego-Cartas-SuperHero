package co.com.sofka.usecase.juego.finalizarjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FinalizarJuegoUseCase {
    private final JuegoRepository juegoRepository;
    private final TarjetaRepository tarjetaRepository;
    private Long tarjetas;

    public Mono<Juego> finalizaJuego(String idJuego) {
        return juegoRepository.findById(idJuego)
                .map(juego -> {
                    tarjetaRepository.findAll()
                            .count()
                            .subscribe(element -> {
                                this.tarjetas = element;
                            });
                    var idGanador = juego.getJugadores().stream()
                            .reduce((acum, jugador) -> {
                                // if (juego.getMazoJuego().size() == 108-jugador.getBaraja().getTarjetas().size()){
                                if (juego.getMazoJuego().size() == this.tarjetas - jugador.getBaraja().getTarjetas().size()) {
                                    return jugador;
                                } else {
                                    return acum;
                                }
                            }).get();
                    juego.setGanador(idGanador.getId());
                    return juego;
                })
                .flatMap(juegoRepository::save);
    }
}