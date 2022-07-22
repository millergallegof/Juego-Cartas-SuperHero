package co.com.sofka.usecase.juego.asignarganador;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AsignarGanadorUseCase {

    private final JuegoRepository juegoRepository;

    public Mono<Juego> asignarGanador(String idJuego, List<Jugador> jugadores) {
        var juego = juegoRepository.findById(idJuego);
        var cartasTotales = jugadores.size() * 5;
        var jug =jugadores.stream()
                .map(ganador -> {
                   if (ganador.getBaraja().getTarjetas().size()  == cartasTotales){
                       return ganador;
                   }else {
                       return null;
                   }
                }).collect(Collectors.toList());
        System.out.println(jug);
//       var ganador =ganadorlist.stream()
//                .reduce((acumulador, winner) -> {
//                    if (acumulador == null){
//                        return winner;
//                    }else {
//                        return acumulador;
//                    }
//                }).get();
//             return juego.map(element -> new Juego(element.getId(),element.getRonda(),ganador.getNickName(),element.getTableroId(),element.getJugadores()));
return null;
    }
}