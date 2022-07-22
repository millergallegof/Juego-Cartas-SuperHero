package co.com.sofka.usecase.juego.comenzarjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.Tablero;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class ComenzarJuegoUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> comenzarJuego(String id,List<Jugador> jugadores, String tableroid) {
        return juegoRepository.findById(id)
                .map(juego -> {
                    juego.setJugadores(jugadores);
                    juego.setTableroId(tableroid);
                    return juego;
                });
    }
}
