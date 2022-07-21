package co.com.sofka.api;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.usecase.juego.crearjuego.CrearJuegoUseCase;
import co.com.sofka.usecase.juego.asignarganador.AsignarGanadorUseCase;
import co.com.sofka.usecase.juego.aumentaronda.AumentaRondaUseCase;
import co.com.sofka.usecase.juego.listarjuego.ListarJuegoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerJuego {
 private final CrearJuegoUseCase crearJuegoUseCase;
 private final AsignarGanadorUseCase asignarGanadorUseCase;
 private final AumentaRondaUseCase aumentaRondaUseCase;
 private final ListarJuegoUseCase listarJuegoUseCase;

    public Mono<ServerResponse> crearJuegoPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(crearJuegoUseCase.crearJuego(e), Juego.class));
    }

    public Mono<ServerResponse> asignarGanadorPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(asignarGanadorUseCase.asignarGanador(element.getJugadores()), Juego.class));
    }

    public Mono<ServerResponse> aumentaRondaPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(aumentaRondaUseCase.aumentaRonda(), Juego.class));
    }

    public Mono<ServerResponse> listarJuegosGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarJuegoUseCase.listarJuego() , Juego.class);
    }
}
