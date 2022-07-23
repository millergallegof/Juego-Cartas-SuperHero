package co.com.sofka.usecase.juego.finalizarjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FinalizarJuegoUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> finalizaJuego(String idJuego) {
         return juegoRepository.findById(idJuego)
                 .map(juego -> {
                 var idGanador = juego.getJugadores().stream()
                         .reduce((acum, jugador) -> {
                            // if (juego.getMazoJuego().size() == 108-jugador.getBaraja().getTarjetas().size()){
                             if (juego.getMazoJuego().size() == 11-jugador.getBaraja().getTarjetas().size()){
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