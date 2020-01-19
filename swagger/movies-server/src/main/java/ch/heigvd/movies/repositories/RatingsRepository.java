package ch.heigvd.movies.repositories;

import ch.heigvd.movies.entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RatingsRepository extends CrudRepository<RatingEntity, Integer> {

    @Query("SELECT r FROM RatingEntity r WHERE r.movieId = :movieId")
    List<RatingEntity> getRatingsByMovieId(@Param("movieId") int movieId);

    @Query("SELECT r FROM RatingEntity r WHERE r.userId = :userId")
    List<RatingEntity> getRatingsByUserId(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query("UPDATE RatingEntity r SET r.rating = :newRating, r.description = :newDescription WHERE r.ratingId = :ratingId")
    void updateRating(@Param("ratingId") int ratingId,
                     @Param("newRating") int newRating,
                     @Param("newDescription") String newDescription);
}
