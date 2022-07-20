package co.com.sofka.api;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.usecase.juego.crearjuego.CrearJuegoUseCase;
import co.com.sofka.usecase.juego.enviarganador.EnviarGanadorUseCase;
import co.com.sofka.usecase.juego.recibircartas.RecibirCartasUseCase;
import co.com.sofka.usecase.juego.recibirjugadores.RecibirJugadoresUseCase;
import co.com.sofka.usecase.tarjeta.listarcartas.ListarCartasUseCase;
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
 private final EnviarGanadorUseCase enviarGanadorUseCase;
 private final RecibirCartasUseCase recibirCartasUseCase;
 private final RecibirJugadoresUseCase recibirJugadoresUseCase;

 private final ListarCartasUseCase listarCartasUseCase;

    public Mono<ServerResponse> crearJuegoPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(crearJuegoUseCase.crearJuego(e), Juego.class));
    }

    public Mono<ServerResponse> enviarGanadorPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(enviarGanadorUseCase.enviarGanador(id, element), Juego.class));
    }

    public Mono<ServerResponse> recibirCartasPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(recibirCartasUseCase.recibirCartas(id, element), Juego.class));
    }

    public Mono<ServerResponse> recibirJugadoresPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(recibirJugadoresUseCase.recibirJugadores(id, element), Juego.class));
    }

    public Mono<ServerResponse> listarJuegosGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarCartasUseCase.listarTarjetas() , Juego.class);
    }
}
