package co.com.sofka.usecase.jugador.obtenertodos;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ObtenerTodosUseCase {

    private JugadorRepository jugadorRepository;

    public Flux<Jugador> ObtenerTodosUseCase(){
        return jugadorRepository.findAll();
    }
}
