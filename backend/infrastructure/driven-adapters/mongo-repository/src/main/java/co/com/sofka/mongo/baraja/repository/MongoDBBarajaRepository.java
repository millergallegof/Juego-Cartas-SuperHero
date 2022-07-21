package co.com.sofka.mongo.baraja.repository;

import co.com.sofka.mongo.baraja.document.BarajaDocument;
import co.com.sofka.mongo.jugador.document.JugadorDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MongoDBBarajaRepository extends ReactiveMongoRepository<BarajaDocument, String>, ReactiveQueryByExampleExecutor<BarajaDocument> {
}
