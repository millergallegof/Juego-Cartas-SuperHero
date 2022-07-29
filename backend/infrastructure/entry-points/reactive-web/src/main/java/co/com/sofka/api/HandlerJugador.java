package co.com.sofka.api;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.usecase.jugador.actualizarbaraja.ActualizarBarajaJugadorUseCase;
import co.com.sofka.usecase.jugador.actualizartarjetasjugador.ActualizarTarjetasJugadorUseCase;
import co.com.sofka.usecase.jugador.apostarcarta.ApostarCartaUseCase;
import co.com.sofka.usecase.jugador.aumentarpuntos.AumentarPuntosUseCase;
import co.com.sofka.usecase.jugador.cambiarestado.CambiarEstadoUseCase;
import co.com.sofka.usecase.jugador.obtenerpuntosjugador.ObtenerPuntosJugadorUseCase;
import co.com.sofka.usecase.jugador.retirarse.RetirarseUseCase;
import co.com.sofka.usecase.jugador.savejugador.SaveJugadorUseCase;
import co.com.sofka.usecase.jugador.obtenertarjetaapostada.ObtenerTarjetaApostadaJugadorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HandlerJugador {
    private final ApostarCartaUseCase apostarCartaUseCase;
    private final RetirarseUseCase retirarseUseCase;
    private final SaveJugadorUseCase saveJugadorUseCase;

    private final CambiarEstadoUseCase cambiarEstadoUseCase;

    private final AumentarPuntosUseCase aumentarPuntosUseCase;

    private final ActualizarBarajaJugadorUseCase actualizarBarajaJugadorUseCase;

    private final ObtenerTarjetaApostadaJugadorUseCase traerTarjetaApostadaJugadorUseCase;
    private final ActualizarTarjetasJugadorUseCase actualizarTarjetasJugadorUseCase;
    private final ObtenerPuntosJugadorUseCase obtenerPuntosJugadorUseCase;

    public Mono<ServerResponse> apostaCartaPutUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest
                .bodyToMono(String.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(apostarCartaUseCase.apostarCarta(id, element), Jugador.class));
    }

    public Mono<ServerResponse> asignarPuntosGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(aumentarPuntosUseCase.aumentarPuntos(id), Jugador.class);

    }

    public Mono<ServerResponse> cambiarEstadoGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cambiarEstadoUseCase.cambiarEstado(id), Jugador.class);
    }

    public Mono<ServerResponse> retirarseGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(retirarseUseCase.retirarse(id), Jugador.class);
    }


    public Mono<ServerResponse> guardarJugadorPostUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Jugador.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(saveJugadorUseCase.SaveJugadorUseCase(e), Jugador.class));
    }

    public Mono<ServerResponse> actualizarBarajaJugadorPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Baraja.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(actualizarBarajaJugadorUseCase.actualizarBarajaJugador(id, element), Jugador.class));
    }

    public Mono<ServerResponse> traerTarjetaApostadaJugadorPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest
                .bodyToMono(String.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(traerTarjetaApostadaJugadorUseCase.TraerTarjetaApostadaJugador(id, element), Tarjeta.class));
    }

    public Mono<ServerResponse> actualizarTarjetasJugadorPOSTUseCase(ServerRequest serverRequest) {
        ParameterizedTypeReference<List<Tarjeta>> modeloPeticion = new ParameterizedTypeReference<List<Tarjeta>>() {
        };
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(modeloPeticion)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(actualizarTarjetasJugadorUseCase.actualizarTarjetas(id, element), Jugador.class));
    }

    public Mono<ServerResponse> obtenerJugadorGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(obtenerPuntosJugadorUseCase.obtenerPuntosJugador(id), Jugador.class);
    }
}