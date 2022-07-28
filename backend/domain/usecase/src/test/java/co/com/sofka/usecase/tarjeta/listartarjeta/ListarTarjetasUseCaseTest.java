package co.com.sofka.usecase.tarjeta.listartarjeta;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import co.com.sofka.usecase.tarjeta.listartarjeta.ListarTarjetasUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class ListarTarjetasUseCaseTest {
    TarjetaRepository tarjetaRepository;
    ListarTarjetasUseCase listTarjetasUsecase;

    @BeforeEach
    public void setup(){
        tarjetaRepository = mock(TarjetaRepository.class);
        listTarjetasUsecase = new ListarTarjetasUseCase(tarjetaRepository);
    }

    @Test
    public void listarTarjetas(){
        var tarjeta = new Tarjeta();
        tarjeta.setId("1");
        tarjeta.setDescripcion("Vamos muchachos");
        tarjeta.setPoderXp(250);
        tarjeta.setImagen("imagencarta");

        when(tarjetaRepository.findAll()).thenReturn(Flux.just(tarjeta));

        StepVerifier.create(listTarjetasUsecase.listarTarjetas())
                .expectNextMatches(idTarjeta ->  idTarjeta.getId().equals("1"))
                .expectComplete()
                .verify();

    }
}