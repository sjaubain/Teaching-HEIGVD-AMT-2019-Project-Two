package ch.heigvd.movies.repositories;

import ch.heigvd.movies.entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<RatingEntity, Integer> {

    @Query("SELECT r FROM RatingEntity r WHERE r.movieId = movieId")
    List<RatingEntity> getRatingsByMovieId(int movieId);
}
