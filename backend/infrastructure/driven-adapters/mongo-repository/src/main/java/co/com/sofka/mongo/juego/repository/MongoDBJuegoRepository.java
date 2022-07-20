package co.com.sofka.mongo.juego.repository;

import co.com.sofka.mongo.juego.document.JuegoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBJuegoRepository extends ReactiveMongoRepository<JuegoDocument, String>, ReactiveQueryByExampleExecutor<JuegoDocument> {
}
