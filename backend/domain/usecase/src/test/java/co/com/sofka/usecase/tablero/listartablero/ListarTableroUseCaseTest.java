package co.com.sofka.usecase.tablero.listartablero;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.usecase.juego.listarjuegos.ListarJuegosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarTableroUseCaseTest {


    TableroRepository repository;
    ListarTableroUseCase listUseCase;


    @BeforeEach
    public void setup() {
        repository = mock(TableroRepository.class);
        listUseCase = new ListarTableroUseCase(repository);
    }

    @Test
    void getValidationTest() {
        var tablero =  new Tablero();
        Tarjeta tarjeta = new Tarjeta("1","1",1,"1");
        tablero.setId("1");
        tablero.setApuesta(new HashMap<>());
        tablero.setGanadorId("1");
        tablero.setTiempo(1);

        when(repository.findAll()).thenReturn(Flux.just(tablero));

        StepVerifier.create(listUseCase.listarTableros())
                .expectNextMatches(idJuego ->  idJuego.getId().equals("1"))
                .expectComplete()
                .verify();
    }
}