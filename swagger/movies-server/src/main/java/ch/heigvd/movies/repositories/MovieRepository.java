package ch.heigvd.movies.repositories;

import ch.heigvd.movies.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, String> {

}
