package co.com.sofka.api;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.usecase.jugador.apostarcarta.ApostarCartaUseCase;
import co.com.sofka.usecase.jugador.guardarjugador.GuardarJugadorUseCase;
import co.com.sofka.usecase.jugador.retirarse.RetirarseUseCase;
import co.com.sofka.usecase.jugador.traerbaraja.TraerbarajaUseCase;
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
private final GuardarJugadorUseCase guardarJugadorUseCase;
private final RetirarseUseCase retirarseUseCase;

private final TraerbarajaUseCase traerbarajaUseCase;
    public Mono<ServerResponse> apostaCartaPutUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest
                .bodyToMono(Jugador.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(apostarCartaUseCase.apostarCarta(id, e), Jugador.class));
    }

    public Mono<ServerResponse> guardarJugadorPostUseCase(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Jugador.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(guardarJugadorUseCase.save(element), Jugador.class));
    }

    public Mono<ServerResponse> retirarsePuttUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.
                bodyToMono(Jugador.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(retirarseUseCase.restirarse(id, element), Jugador.class));
    }

    public Mono<ServerResponse> TreaerPutBaraja(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.
                bodyToMono(Jugador.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(traerbarajaUseCase.traerBaraja(id, element), Jugador.class));
    }
}
