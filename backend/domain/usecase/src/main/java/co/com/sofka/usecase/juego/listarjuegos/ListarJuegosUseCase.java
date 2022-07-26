package co.com.sofka.usecase.juego.listarjuegos;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListarJuegosUseCase {

    private final JuegoRepository juegoRepository;

    public Flux<Juego> listarJuego() {
        return juegoRepository.findAll();
    }

}
