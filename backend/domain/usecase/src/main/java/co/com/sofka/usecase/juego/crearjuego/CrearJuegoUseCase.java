package co.com.sofka.usecase.juego.crearjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CrearJuegoUseCase {
    private final JuegoRepository juegoRepository;
    private final TarjetaRepository tarjetaRepository;

    public Mono<Juego> crearJuego(Juego juego) {
        return tarjetaRepository.findAll()
                .collectList()
                .map(tarjetas -> {
                    juego.setCreateAt(LocalDateTime.now());
                    juego.setMazoJuego(tarjetas);
                    return juego;
                })
                .flatMap(juegoRepository::save);
    }
}