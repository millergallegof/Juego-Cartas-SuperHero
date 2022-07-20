package co.com.sofka.mongo.juego.repository;

import co.com.sofka.model.juego.Juego;
import co.com.sofka.model.juego.gateways.JuegoRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.tarjeta.repository.MongoDBTarjetaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoJuegoRepositoryAdapter extends AdapterOperations<Juego,JuegoDocument, String, MongoDBJuegoRepository>
        implements JuegoRepository {


    public MongoJuegoRepositoryAdapter(MongoDBJuegoRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Juego.class));
    }
    }
