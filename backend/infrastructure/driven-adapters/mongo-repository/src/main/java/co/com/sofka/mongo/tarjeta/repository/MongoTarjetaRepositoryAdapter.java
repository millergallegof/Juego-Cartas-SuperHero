package co.com.sofka.mongo.tarjeta.repository;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.model.tarjeta.gateways.TarjetaRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.tarjeta.document.TarjetaDocument;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoTarjetaRepositoryAdapter extends AdapterOperations<Tarjeta, TarjetaDocument, String, MongoDBTarjetaRepository>
    implements TarjetaRepository
{

    public MongoTarjetaRepositoryAdapter(MongoDBTarjetaRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Tarjeta.class));
    }


    @Override
    public Mono<Tarjeta> update(String id, Tarjeta tarjeta) {
        return null;
    }

    @Override
    public Flux<Tarjeta> listarBaraja() {
        return null;
    }
}
