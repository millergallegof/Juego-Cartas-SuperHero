package co.com.sofka.mongo.tarjeta.repository;

import co.com.sofka.mongo.juego.document.JuegoDocument;
import co.com.sofka.mongo.tarjeta.document.TarjetaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBTarjetaRepository extends ReactiveMongoRepository<TarjetaDocument, String>, ReactiveQueryByExampleExecutor<TarjetaDocument> {
}
