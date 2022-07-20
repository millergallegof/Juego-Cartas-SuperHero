package co.com.sofka.mongo.tarjeta.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBTarjetaRepository extends ReactiveMongoRepository<Object/* change for adapter model */, String>, ReactiveQueryByExampleExecutor<Object/* change for adapter model */> {
}
