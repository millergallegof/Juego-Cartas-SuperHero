package co.com.sofka.usecase.tablero.enviartarjetasganador;

import co.com.sofka.model.tablero.gateways.TableroRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EnviarTarjetasGanadorUseCase {
    private final TableroRepository tableroRepository;

    public Flux<Tarjeta> enviarTarjetasGanador(String id) {
        return tableroRepository.findById(id)
                .map(tablero -> {
                    var listaCartas = tablero.getApuesta().entrySet().stream()
                            .map(apuesta -> {
                                return apuesta.getValue();
                            }).collect(Collectors.toList());
                    return listaCartas;
                }).flatMapMany(Flux::fromIterable);
    }
}
