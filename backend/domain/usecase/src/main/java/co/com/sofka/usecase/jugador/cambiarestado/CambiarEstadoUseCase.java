package co.com.sofka.usecase.jugador.cambiarestado;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CambiarEstadoUseCase {

    private final JugadorRepository jugadorRepository;

    public Mono<Jugador> cambiarEstado(String jugadorId) {
        return jugadorRepository.findById(jugadorId)
                .map(element -> {
                    if (element.getEstado()==false){
                        element.setEstado(true);
                    } else if (element.getEstado()==true) {
                        element.setEstado(false);
                    }
                    return element;
                }).flatMap(jugadorRepository::save);
    }
}
