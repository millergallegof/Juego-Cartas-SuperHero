package co.com.sofka.mongo.baraja.repository;

import co.com.sofka.model.jugador.Jugador;
import co.com.sofka.model.jugador.JugadorId;
import co.com.sofka.model.jugador.gateways.JugadorRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.jugador.document.JugadorDocument;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoBarajaRepositoryAdapter extends AdapterOperations<Jugador, JugadorDocument, String, MongoDBBarajaRepository>
 implements JugadorRepository
{

    public MongoBarajaRepositoryAdapter(MongoDBBarajaRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d,  Jugador.class/* change for domain model */));
    }


    @Override
    public Mono<Jugador> apostarCarta(JugadorId idJugador, Jugador jugador) {
        jugador.setJugadorId(idJugador);
        return repository
                .save(new JugadorDocument(jugador.getJugadorId().getClass().getName(), jugador.getNickName(), jugador.getPuntos(), jugador.getBaraja(), jugador.getEstado()))
                .flatMap(x -> Mono.just(jugador));
    }

    @Override
    public Mono<Jugador> restirarse(JugadorId idJugador, Jugador jugador) {
        jugador.setJugadorId(idJugador);
        jugador.setEstado(false);
        return repository
                .save(new JugadorDocument(jugador.getJugadorId().getClass().getName(), jugador.getNickName(), jugador.getPuntos(), jugador.getBaraja(), jugador.getEstado()))
                .flatMap(x -> Mono.just(jugador));
    }


}
