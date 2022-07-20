package co.com.sofka.mongo.tarjeta.repository;

import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoTarjetaRepositoryAdapter extends AdapterOperations<Object/* change for domain model */, Object/* change for adapter model */, String, MongoDBTarjetaRepository>
// implements ModelRepository from domain
{

    public MongoTarjetaRepositoryAdapter(MongoDBTarjetaRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Object.class/* change for domain model */));
    }
}
