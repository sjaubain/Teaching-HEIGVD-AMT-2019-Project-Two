package ch.heigvd.movies.repositories;

import ch.heigvd.movies.entities.MovieEntity;
import ch.heigvd.movies.entities.RatingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends PagingAndSortingRepository<MovieEntity, Integer> {

}
