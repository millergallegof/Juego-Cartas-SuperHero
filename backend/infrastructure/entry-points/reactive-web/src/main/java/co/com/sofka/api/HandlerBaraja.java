package co.com.sofka.api;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.usecase.baraja.crearbaraja.CrearBarajaUseCase;

import co.com.sofka.usecase.baraja.enviarbajara.EnviarBajaraUseCase;
import co.com.sofka.usecase.juego.asignarganador.AsignarGanadorUseCase;
import co.com.sofka.usecase.juego.aumentaronda.AumentaRondaUseCase;
import co.com.sofka.usecase.juego.crearjuego.CrearJuegoUseCase;
import co.com.sofka.usecase.juego.listarjuego.ListarJuegoUseCase;
import co.com.sofka.usecase.tarjeta.listartarjeta.ListarTarjetasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerBaraja {
 private final CrearBarajaUseCase crearBarajaUseCase;

 private final EnviarBajaraUseCase enviarBajaraUseCase;

    public Mono<ServerResponse> crearBarajaGETUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Baraja.class)
                .flatMap(e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(crearBarajaUseCase.crearBaraja(), Baraja.class));
    }

    public Mono<ServerResponse> listarTarjetasGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(enviarBajaraUseCase.enviarBaraja(), Tarjeta.class);
    }


}
