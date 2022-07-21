package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@CrossOrigin(origins = "*")
@Configuration
public class RouterRest {
@Bean
public RouterFunction<ServerResponse> routerFunction(HandlerTarjeta handlerTarjeta, HandlerJuego handlerJuego, HandlerJugador handlerJugador) {
    return route(POST("/api/tarjeta/crear"), handlerTarjeta::crearTarjetaPOSTUseCase).andRoute
            (DELETE("/api/tarjeta/eliminar/{id}"), handlerTarjeta::eliminarTarjetaDELETEUseCase).andRoute
            (POST("/api/tarjeta/actualizar/{id}"), handlerTarjeta::actualizarPOSTUseCase).andRoute
            (GET("/api/tarjeta/listar"), handlerTarjeta::listarTarjetasGETUseCase).andRoute
            (POST("/api/juego/crear"), handlerJuego::crearJuegoPOSTUseCase).andRoute
            (POST("/api/juego/ganador/{id}"), handlerJuego::enviarGanadorPOSTUseCase).andRoute
            (POST("/api/juego/cartas/{id}"), handlerJuego::recibirCartasPOSTUseCase).andRoute
            (POST("/api/juego/jugador/{id}"), handlerJuego::recibirJugadoresPOSTUseCase).andRoute
            (POST("/api/jugador/crear"), handlerJugador::guardarJugadorPostUseCase).andRoute
            (POST("/api/jugador/carta/{id}"), handlerJugador::apostaCartaPutUseCase).andRoute
            (POST("/api/jugador/retirarse/{id}"), handlerJugador::retirarsePOSTUseCase).andRoute
            (POST("/api/jugador/baraja/{id}"), handlerJugador::traerJugadorPOSTBaraja).andRoute
            (GET("/api/jugador/obtejerjugador"), handlerJugador::obtenerJugadorPost).andRoute
            (GET("/api/juego/listar"), handlerJuego::listarJuegosGETUseCase);

    }
}
