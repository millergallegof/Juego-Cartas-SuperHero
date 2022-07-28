package co.com.sofka.usecase.tablero.createtablero;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.usecase.tarjeta.createtarjeta.CreateTarjetaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateTableroUseCaseTest {

    @MockBean
    TableroRepository tableroRepository;

    @SpyBean
    CreateTableroUseCase createTableroUseCase;

    @BeforeEach
    public void setUp(){
        tableroRepository = mock(TableroRepository.class);
        createTableroUseCase = new CreateTableroUseCase(tableroRepository);
    }

    @Test
    void testCreateTarjetaUseCase(){
        Jugador jugador = new Jugador("1","daniel",1,null,true);
        Tarjeta tarjeta1 = new Tarjeta("1", "hulk", 150, "url");
        Map<String, Tarjeta> tarje = new HashMap<>();
        tarje.put(jugador.getId(), tarjeta1);
        Tablero tablero = new Tablero("1",tarje,"1" ,2);
        Mono<Tablero> tableroMono =  Mono.just(tablero);

        when(tableroRepository.save(Mockito.any(Tablero.class))).thenReturn(tableroMono);

        StepVerifier.create(createTableroUseCase.crearTarjeta(tablero))
                .expectNextMatches(idTarjeta -> idTarjeta.getId().equals("1"))
                .expectComplete()
                .verify();

    }

}