package co.com.sofka.mongo.juego.repository;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.juego.document.JuegoDocument;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class MongoJuegoRepositoryAdapter extends AdapterOperations<Juego, JuegoDocument, String, MongoDBJuegoRepository>
        implements JuegoRepository {


    public MongoJuegoRepositoryAdapter(MongoDBJuegoRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Juego.class));
    }

    @Override
    public Flux<Jugador> recibirJugadores(List<Jugador> jugadores) {
      //  repository.findAll()
       //         .flatMap()
       // return repository.save(new JuegoDocument(juego.getId(), juego.getRonda(),juego.getTarjetas(),juego.getJugadores()))
        //        .flatMap(e -> Mono.just(juego));
        return null;
    }

    @Override
    public Flux<Tarjeta> recibirCartas(List<Tarjeta> tarjetas) {
        return null;
    }

    //   @Override
  //  public Flux<Juego> recibirCartas(List<Tarjeta> tarjetas) {
      //  juego.setId(id);
       // return repository.save(new JuegoDocument(juego.getId(), juego.getRonda(),juego.getTarjetas(),juego.getJugadores()))
        //        .flatMap(e -> Mono.just(juego));
  //      return null;
  //  }

    @Override
    public Mono<Juego> enviarGanador(String id, Juego juego) {
        juego.setId(id);
        return repository.save(new JuegoDocument(juego.getId(), juego.getRonda(),juego.getTarjetas(),juego.getJugadores()))
                .flatMap(e -> Mono.just(juego));
    }

}
