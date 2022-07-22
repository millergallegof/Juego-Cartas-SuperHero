package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(HandlerTarjeta handlerTarjeta, HandlerJuego handlerJuego, HandlerJugador handlerJugador, HandlerBaraja handlerBaraja, HandlerTablero handlerTablero) {
        return route(POST("/api/tarjeta/crear"), handlerTarjeta::crearTarjetaPOSTUseCase).andRoute
                (DELETE("/api/tarjeta/eliminar/{id}"), handlerTarjeta::eliminarTarjetaDELETEUseCase).andRoute
                (POST("/api/tarjeta/actualizar/{id}"), handlerTarjeta::actualizarPOSTUseCase).andRoute
                (GET("/api/tarjeta/listar"), handlerTarjeta::listarTarjetasGETUseCase).andRoute
                (POST("/api/juego/crear"), handlerJuego::crearJuegoPOSTUseCase).andRoute
                (POST("/api/juego/ganador/{id}"), handlerJuego::asignarGanadorPOSTUseCase).andRoute
                (GET("/api/juego/rondas/{id}"), handlerJuego::aumentaRondaPOSTUseCase).andRoute
                (GET("/api/juego/listar"), handlerJuego::listarJuegosGETUseCase).andRoute
                (POST("/api/juego/comenzar/{id}"), handlerJuego::comenzarJuegoPOSTUseCase).andRoute
                (POST("/api/jugador/carta/{id}"), handlerJugador::apostaCartaPutUseCase).andRoute
                (POST("/api/jugador/crear"), handlerJugador::guardarJugadorPostUseCase).andRoute
                (POST("/api/jugador/retirarse/{id}"), handlerJugador::retirarsePOSTUseCase).andRoute
                (GET("/api/baraja/crear"), handlerBaraja::crearBarajaGETUseCase).andRoute
                (GET("/api/baraja/listar"), handlerBaraja::listarBarajaGETUseCase).andRoute
                (GET("/api/tablero/listar"), handlerTablero::listarTablerosGETUseCase).andRoute
                (POST("/api/tablero/recibir/card/{id}"), handlerTablero::recibirTarjetaPOSTUseCase).andRoute
                (GET("/api/tablero/ganador/{id}"), handlerTablero::elegirGanadorGETUseCase).andRoute
                (GET("/api/tablero/ganador/tarjetas/{id}"), handlerTablero::enviarTarjetasGanadorGETUseCase).andRoute
                (POST("/api/tablero/crear"), handlerTablero::crearTableroPOSTUseCase);
    }
}
