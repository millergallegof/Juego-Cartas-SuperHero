package co.com.sofka.api;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.usecase.jugador.apostarcarta.ApostarCartaUseCase;
import co.com.sofka.usecase.jugador.guardarjugador.GuardarJugadorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerJugador {
private  final ApostarCartaUseCase apostarCartaUseCase;
private  final GuardarJugadorUseCase guardarJugadorUseCase;
    public Mono<ServerResponse> apostaCartaUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest
                .bodyToMono(Jugador.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(apostarCartaUseCase.apostarCarta(id, e), Jugador.class));
    }

    public Mono<ServerResponse> guardarJugadorUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest
                .bodyToMono(Jugador.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(guardarJugadorUseCase.save(element), Jugador.class));
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // usecase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
