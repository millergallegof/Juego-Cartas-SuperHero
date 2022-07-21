package co.com.sofka.mongo.tablero.repository;

import co.com.sofka.mongo.tablero.document.TableroDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBTableroRepository extends ReactiveMongoRepository<TableroDocument, String>, ReactiveQueryByExampleExecutor<TableroDocument> {

}
