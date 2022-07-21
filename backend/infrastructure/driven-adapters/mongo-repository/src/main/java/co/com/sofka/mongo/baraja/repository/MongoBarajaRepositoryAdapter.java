package co.com.sofka.mongo.baraja.repository;

import co.com.sofka.model.baraja.Baraja;
import co.com.sofka.model.baraja.gateways.BarajaRepository;
import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.JugadorId;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.mongo.baraja.document.BarajaDocument;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.jugador.document.JugadorDocument;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoBarajaRepositoryAdapter extends AdapterOperations<Baraja, BarajaDocument, String, MongoDBBarajaRepository>
 implements BarajaRepository
{

    public MongoBarajaRepositoryAdapter(MongoDBBarajaRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d,Baraja.class));
    }


}
