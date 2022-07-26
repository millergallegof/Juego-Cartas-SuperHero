package co.com.sofka.api;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.usecase.tablero.createtablero.CreateTableroUseCase;
import co.com.sofka.usecase.tablero.elegirganadortablero.ElegirGanadorTableroUseCase;
import co.com.sofka.usecase.tablero.eliminarcartastablero.EliminarTarjetasTableroUseCase;
import co.com.sofka.usecase.tablero.eliminartablero.EliminarTableroUseCase;
import co.com.sofka.usecase.tablero.enviartarjetasganador.EnviarTarjetasGanadorUseCase;
import co.com.sofka.usecase.tablero.listartablero.ListarTableroUseCase;
import co.com.sofka.usecase.tablero.obtenertableroporidusecase.ObtenerTableroPorIdUseCase;
import co.com.sofka.usecase.tablero.recibirtarjeta.RecibirTarjetaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HandlerTablero {

    private final CreateTableroUseCase createTableroUseCase;
    private final ListarTableroUseCase listarTableroUseCase;
    private final RecibirTarjetaUseCase recibirTarjetaUseCase;
    private final ElegirGanadorTableroUseCase elegirGanadorTableroUseCase;
    private final EnviarTarjetasGanadorUseCase enviarTarjetasGanadorUseCase;
    private final EliminarTarjetasTableroUseCase eliminarTarjetasTableroUseCase;
    private final ObtenerTableroPorIdUseCase obtenerTableroPorIdUseCase;

    private final EliminarTableroUseCase eliminarTableroUseCase;

    public Mono<ServerResponse> crearTableroPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Tablero.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createTableroUseCase.crearTarjeta(e), Tablero.class));
    }

    public Mono<ServerResponse> listarTablerosGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarTableroUseCase.listarTableros(), Tablero.class);
    }

    public Mono<ServerResponse> recibirTarjetaPOSTUseCase(ServerRequest serverRequest) {
        ParameterizedTypeReference<Map<String, Tarjeta>> modeloPeticion = new ParameterizedTypeReference<Map<String, Tarjeta>>() {
        };
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(modeloPeticion)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(recibirTarjetaUseCase.recibirTarjetas(id, element), Tablero.class));
    }
    public Mono<ServerResponse> elegirGanadorGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(elegirGanadorTableroUseCase.elegirGanador(id), Tablero.class);
    }

    public Mono<ServerResponse> enviarTarjetasGanadorGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(enviarTarjetasGanadorUseCase.enviarTarjetasGanador(id), Tarjeta.class);
    }

    public Mono<ServerResponse> eliminarTarjetasPOSTUseCase(ServerRequest serverRequest) {
        var idTablero = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eliminarTarjetasTableroUseCase.eliminarCartas(idTablero), Tablero.class);
    }

    public Mono<ServerResponse> obtenerTableroGETUseCase(ServerRequest serverRequest) {
        var idTablero = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(obtenerTableroPorIdUseCase.obtenerTablero(idTablero), Tablero.class);
    }

    public Mono<ServerResponse> eliminarTableroDELETEUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(eliminarTableroUseCase.eliminarTablero(id), Tablero.class);
    }

}
