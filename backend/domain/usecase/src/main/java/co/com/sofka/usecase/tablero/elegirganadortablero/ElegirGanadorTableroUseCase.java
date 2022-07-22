package co.com.sofka.usecase.tablero.elegirganadortablero;

import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ElegirGanadorTableroUseCase {

    private final TableroRepository tableroRepository;

    public Mono<Tablero> elegirGanador(String id) {
        return tableroRepository.findById(id)
                .map(tablero -> {
                    var idGanador = tablero.getApuesta().entrySet().stream()
                            .reduce((acum, apu) -> {
                                if (acum.getValue().getPoderXp() < apu.getValue().getPoderXp()) {
                                    return apu;
                                } else {
                                    return acum;
                                }
                            })
                            .get().getKey();
                    tablero.setGanadorId(idGanador);
                    return tablero;
                })
                .flatMap(tableroRepository::save);
    }
}
