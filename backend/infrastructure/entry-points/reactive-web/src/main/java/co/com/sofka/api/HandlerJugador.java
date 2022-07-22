package co.com.sofka.api;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.usecase.jugador.apostarcarta.ApostarCartaUseCase;
import co.com.sofka.usecase.jugador.retirarse.RetirarseUseCase;
import co.com.sofka.usecase.jugador.savejugador.SaveJugadorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerJugador {
private final ApostarCartaUseCase apostarCartaUseCase;
private final RetirarseUseCase retirarseUseCase;

private final SaveJugadorUseCase saveJugadorUseCase;


    public Mono<ServerResponse> apostaCartaPutUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest
                .bodyToMono(Jugador.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(apostarCartaUseCase.apostarCarta(id, e), Jugador.class));
    }

    public Mono<ServerResponse> retirarsePOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Jugador.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(retirarseUseCase.retirarse(id, element), Jugador.class));
    }

    public Mono<ServerResponse> guardarJugadorPostUseCase(ServerRequest serverRequest) {
        return  serverRequest.bodyToMono(Jugador.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(saveJugadorUseCase.SaveJugadorUseCase(e), Jugador.class));
    }
}
