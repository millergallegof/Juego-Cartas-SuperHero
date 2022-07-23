package co.com.sofka.api;

import co.com.sofka.model.juego.ElementosJuego;
import co.com.sofka.model.juego.ElementosJugadorJuego;
import co.com.sofka.model.juego.Juego;
import co.com.sofka.usecase.juego.actualizarganador.ActualizarGanadorRondaUseCase;
import co.com.sofka.usecase.juego.asignarcartaretirojugador.AsignarCartaRetiroJugadorUseCase;
import co.com.sofka.usecase.juego.comenzarjuego.ComenzarJuegoUseCase;
import co.com.sofka.usecase.juego.crearjuego.CrearJuegoUseCase;
import co.com.sofka.usecase.juego.asignarganador.AsignarGanadorUseCase;
import co.com.sofka.usecase.juego.aumentaronda.AumentaRondaUseCase;
import co.com.sofka.usecase.juego.listarjuego.ListarJuegoUseCase;
import co.com.sofka.usecase.juego.repartirbaraja.RepartirBarajaUseCase;
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
    private final ComenzarJuegoUseCase comenzarJuegoUseCase;
    private final RepartirBarajaUseCase repartirBarajaUseCase;
    private final ActualizarGanadorRondaUseCase actualizarGanadorRondaUseCase;
    private final AsignarCartaRetiroJugadorUseCase asignarCartaRetiroJugadorUseCase;

    public Mono<ServerResponse> crearJuegoPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(crearJuegoUseCase.crearJuego(e), Juego.class));
    }

    public Mono<ServerResponse> asignarGanadorPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(asignarGanadorUseCase.asignarGanador(id, element.getJugadores()), Juego.class));
    }

    public Mono<ServerResponse> aumentaRondaPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Juego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(aumentaRondaUseCase.aumentarRonda(id), Juego.class));
    }

    public Mono<ServerResponse> listarJuegosGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarJuegoUseCase.listarJuego(), Juego.class);
    }

    public Mono<ServerResponse> comenzarJuegoPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(ElementosJuego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(comenzarJuegoUseCase.comenzarJuego(id, element.getJugadores(), element.getIdTablero()), Juego.class));
    }

    public Mono<ServerResponse> repartirBarajaGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repartirBarajaUseCase.repartirBaraja(id), Juego.class);
    }

    public Mono<ServerResponse> actuaLizarBarajaGanadorRondaPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(ElementosJugadorJuego.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(actualizarGanadorRondaUseCase.actualizarGanadorRonda(id, element.getIdJugador(), element.getTarjetas()), Juego.class));
    }

    public Mono<ServerResponse> asignarCartasMazoPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(String.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(asignarCartaRetiroJugadorUseCase.asignarCartasMazo(id, element), Juego.class));
    }

}
