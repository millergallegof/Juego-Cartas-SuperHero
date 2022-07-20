package co.com.sofka.mongo.jugador.repository;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.jugador.document.JugadorDocument;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoJugadorRepositoryAdapter extends AdapterOperations<Jugador, JugadorDocument, String, MongoDBJugadorRepository>
 implements JugadorRepository
{

    public MongoJugadorRepositoryAdapter(MongoDBJugadorRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d,  Jugador.class/* change for domain model */));
    }


    @Override
    public Mono<Jugador> apostarCarta(String idJugador, Jugador jugador) {
        jugador.setIdentificador(idJugador);
        return repository
                .save(new JugadorDocument(jugador.getIdentificador(), jugador.getPuntos(), jugador.getBaraja(), jugador.getEstado(), jugador.getNickName()))
                .flatMap(x -> Mono.just(jugador));
    }

    @Override
    public Mono<Jugador> restirarse(String idJugador, Jugador jugador) {
        jugador.setIdentificador(idJugador);
        jugador.setEstado(false);
        return repository
                .save(new JugadorDocument(jugador.getIdentificador(), jugador.getPuntos(), jugador.getBaraja(), jugador.getEstado(), jugador.getNickName()))
                .flatMap(x -> Mono.just(jugador));
    }

    @Override
    public Mono<Jugador> traerBaraja(String idJugador, Jugador jugador) {
        jugador.setIdentificador(idJugador);
        return repository
                .save(new JugadorDocument(jugador.getIdentificador(), jugador.getPuntos(), jugador.getBaraja(), jugador.getEstado(), jugador.getNickName()))
                .flatMap(x -> Mono.just(jugador));
    }


}
