package co.com.sofka.api;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.usecase.baraja.crearbaraja.CrearBarajaUseCase;
import co.com.sofka.usecase.baraja.enviarbajara.EnviarBajaraUseCase;
import co.com.sofka.usecase.tablero.createtablero.CreateTableroUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerTablero {

    private final CreateTableroUseCase createTableroUseCase;


    public Mono<ServerResponse> crearTableroPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Tablero.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createTableroUseCase.crearTarjeta(e), Tablero.class));
    }

}
