package co.com.sofka.usecase.finalizarjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.List;

@RequiredArgsConstructor
public class FinalizarJuegoUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> asignarGanador(String idJuego, List<Jugador> jugadores) {
         return juegoRepository.findById(idJuego)
                 .map(juego -> {
                 var nickGanador = jugadores.stream()
                         .reduce((acum, jugador) -> {
                             if (juego.getJugadores().size() * 5 < jugador.getBaraja().getTarjetas().size()){
                                 return jugador;
                             } else {
                                 return acum;
                             }
                         }).get();
                         juego.setGanador(nickGanador.getNickName());
                     return juego;
                 })
                 .flatMap(juegoRepository::save);
    }
}