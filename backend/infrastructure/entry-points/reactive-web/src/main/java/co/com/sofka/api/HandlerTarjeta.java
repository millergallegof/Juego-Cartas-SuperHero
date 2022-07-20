package co.com.sofka.api;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.usecase.tarjeta.actualizartarjeta.ActualizarTarjetaUseCase;
import co.com.sofka.usecase.tarjeta.createtarjeta.CreateTarjetaUseCase;
import co.com.sofka.usecase.tarjeta.deletetarjeta.DeleteTarjetaUseCase;
import co.com.sofka.usecase.tarjeta.listarcartas.ListarCartasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerTarjeta {
    private final CreateTarjetaUseCase createTarjetaUseCase;
    private final ActualizarTarjetaUseCase actualizarTarjetaUseCase;
    private final DeleteTarjetaUseCase deleteTarjetaUseCase;
    private final ListarCartasUseCase listarCartasUseCase;

    public Mono<ServerResponse> crearTarjetaPOSTUseCase(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Tarjeta.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createTarjetaUseCase.crearTarjeta(element), Tarjeta.class));
    }

    public Mono<ServerResponse> eliminarTarjetaDELETEUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleteTarjetaUseCase.eliminarTarjeta(id), Tarjeta.class);
    }

    public Mono<ServerResponse> actualizarPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Tarjeta.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(actualizarTarjetaUseCase.actualizarTarjeta(id, element), Tarjeta.class));
    }

    public Mono<ServerResponse> listarTarjetasGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarCartasUseCase.listarTarjetas(), Tarjeta.class);
    }
}
