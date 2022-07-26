package co.com.sofka.usecase.juego.listarjuegos;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ListarJuegosUseCaseTest {


    JuegoRepository repository;
    ListarJuegosUseCase listUseCase;


    @BeforeEach
    public void setup() {
        repository = mock(JuegoRepository.class);
        listUseCase = new ListarJuegosUseCase(repository);
    }

    @Test
    void getValidationTest() {
        var juego =  new Juego();
        List<Tarjeta> listaTarjetas = new ArrayList<>();
        List<Jugador> listaJugadores = new ArrayList<>();
        juego.setId("1");
        juego.setRonda(1);
        juego.setMazoJuego(listaTarjetas);
        juego.setGanador("daniel");
        juego.setTableroId("1");
        juego.setJugadores(listaJugadores);

        when(repository.findAll()).thenReturn(Flux.just(juego));

        StepVerifier.create(listUseCase.listarJuego())
                .expectNextMatches(idJuego ->  idJuego.getId().equals("1"))
                .expectComplete()
                .verify();
    }
}