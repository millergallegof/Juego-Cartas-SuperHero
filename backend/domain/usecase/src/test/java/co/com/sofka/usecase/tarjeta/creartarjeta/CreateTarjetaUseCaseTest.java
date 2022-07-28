package co.com.sofka.usecase.tarjeta.creartarjeta;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import co.com.sofka.usecase.tarjeta.createtarjeta.CreateTarjetaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class CreateTarjetaUseCaseTest {

    @MockBean
    TarjetaRepository tarjetaRepository;
    @SpyBean
    CreateTarjetaUseCase createTarjetaUseCase;

    @BeforeEach
    public void setUp(){
        tarjetaRepository = mock(TarjetaRepository.class);
        createTarjetaUseCase = new CreateTarjetaUseCase(tarjetaRepository);
    }

    @Test
    void testCreateTarjetaUseCase(){
        Tarjeta tarjeta = new Tarjeta("1","Esto es una tarjeta", 200,"url-img" );
        Mono<Tarjeta> tarjetaMono =  Mono.just(tarjeta);

        when(tarjetaRepository.save(Mockito.any(Tarjeta.class))).thenReturn(tarjetaMono);

        StepVerifier.create(createTarjetaUseCase.crearTarjeta(tarjeta))
                .expectNextMatches(idTarjeta -> idTarjeta.getId().equals("1"))
                .expectComplete()
                .verify();
    }
}