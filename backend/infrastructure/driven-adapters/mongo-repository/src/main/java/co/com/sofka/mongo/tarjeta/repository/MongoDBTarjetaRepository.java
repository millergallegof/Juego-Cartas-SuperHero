package co.com.sofka.mongo.tarjeta.repository;

import co.com.sofka.model.tarjeta.Tarjeta;
import co.com.sofka.mongo.tarjeta.document.TarjetaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MongoDBTarjetaRepository extends ReactiveMongoRepository<Tarjeta, String>, ReactiveQueryByExampleExecutor<Tarjeta>, ReactiveCrudRepository<TarjetaDocument, String> {

}
