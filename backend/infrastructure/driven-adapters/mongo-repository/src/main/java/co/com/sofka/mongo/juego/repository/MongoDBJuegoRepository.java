package co.com.sofka.mongo.juego.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBJuegoRepository extends ReactiveMongoRepository<JuegoDocument, String>, ReactiveQueryByExampleExecutor<JuegoDocument> {
}
