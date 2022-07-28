package co.com.sofka.usecase.juego.crearjuego;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CrearJuegoUseCaseTest {
    @SpyBean
    CrearJuegoUseCase crearJuegoUseCase;

    @MockBean
    JuegoRepository juegoRepository;

    @MockBean
    TarjetaRepository tarjetaRepository;

    @BeforeEach
    public void setup(){
        juegoRepository = mock(JuegoRepository.class);
        tarjetaRepository = mock(TarjetaRepository.class);
        crearJuegoUseCase = new CrearJuegoUseCase(juegoRepository,tarjetaRepository);
    }

    @Test
    void creaJuegoUseCaseTest() {
        Tarjeta tarjeta1 = new Tarjeta("1", "hulk", 230, "url");
        Tarjeta tarjeta2 = new Tarjeta("2", "pepito", 230, "url");
        Flux<Tarjeta> tarjetasFlux = Flux.just(tarjeta1, tarjeta2);
        //arrange
        List<Tarjeta> listaTarjetas = new ArrayList<>();
        List<Jugador> listaJugadores = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        listaTarjetas.add(new Tarjeta("1","asd",2,"asda"));
        listaJugadores.add(new Jugador("1","as",2,true));
        Juego juego = new Juego("1",1,listaTarjetas,"","",listaJugadores,date);
        Mono<Juego> juegoMono = Mono.just(juego);

        when(juegoRepository.save(Mockito.any(Juego.class))).thenReturn(juegoMono);
        when(tarjetaRepository.findAll()).thenReturn(tarjetasFlux);


        StepVerifier.create(crearJuegoUseCase.crearJuego(juego))
                .expectNextMatches(idJuego ->  idJuego.getId().equals("1"))
                .expectComplete()
                .verify();
    }
}