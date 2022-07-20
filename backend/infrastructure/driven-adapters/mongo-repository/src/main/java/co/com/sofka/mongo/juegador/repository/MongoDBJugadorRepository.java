package co.com.sofka.mongo.juegador.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MongoDBJugadorRepository extends ReactiveMongoRepository<JugadorDocument, String>, ReactiveQueryByExampleExecutor<JugadorDocument>, ReactiveCrudRepository<JugadorDocument, String> {
}
