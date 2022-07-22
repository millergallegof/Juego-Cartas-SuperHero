package co.com.sofka.mongo.tablero.repository;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.tablero.Tablero;
import co.com.sofka.model.tablero.gateways.TableroRepository;
import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.jugador.repository.MongoDBJugadorRepository;
import co.com.sofka.mongo.tablero.document.TableroDocument;
import co.com.sofka.mongo.tarjeta.document.TarjetaDocument;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoTableroRepositoryAdapter extends AdapterOperations<Tablero, TableroDocument, String, MongoDBTableroRepository>
 implements TableroRepository
{

    public MongoTableroRepositoryAdapter(MongoDBTableroRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d,  Tablero.class));
    }



}
