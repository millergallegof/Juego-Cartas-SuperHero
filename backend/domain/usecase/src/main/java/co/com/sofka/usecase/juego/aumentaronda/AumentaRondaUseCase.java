package co.com.sofka.usecase.juego.aumentaronda;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AumentaRondaUseCase {
    private final JuegoRepository juegoRepository;

    public Mono<Juego> aumentarRonda(String idJuego) {
        return juegoRepository.findById(idJuego)
                .map(element -> {
                    element.setRonda(element.getRonda()+1);
                    return element;
                }).flatMap(juegoRepository::save);
    }
}
