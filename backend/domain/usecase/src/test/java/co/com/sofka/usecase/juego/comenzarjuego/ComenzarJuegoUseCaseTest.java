package co.com.sofka.usecase.juego.comenzarjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

class ComenzarJuegoUseCaseTest {

    @SpyBean
    ComenzarJuegoUseCase comenzarJuegoUseCase;

    @MockBean
    JuegoRepository juegoRepository;


    @BeforeEach
    public void setUp(){
        juegoRepository = mock(JuegoRepository.class);
        comenzarJuegoUseCase = new ComenzarJuegoUseCase(juegoRepository);
    }

    @Test
    void comenzarJuego() {
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add( new Jugador("1", "Hernan", 0, true));
        List<Tarjeta> mazoCartas = new ArrayList<>();
//        Juego juego = new Juego("1", 1, mazoCartas, "", "2",jugadores);
//        Mono<Juego> monoJuego = Mono.just(juego);

//        when(juegoRepository.save(Mockito.any(Juego.class))).thenReturn(monoJuego);

        StepVerifier.create(comenzarJuegoUseCase.comenzarJuego("1",jugadores,"2"))
                .expectNextMatches(idTablero -> idTablero.getId().equals("2"))
                .expectComplete()
                .verify();
    }
}
