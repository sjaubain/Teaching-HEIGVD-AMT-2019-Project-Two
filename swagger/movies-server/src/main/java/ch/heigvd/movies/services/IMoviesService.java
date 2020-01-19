package ch.heigvd.movies.services;

import ch.heigvd.movies.api.dto.Movie;
import ch.heigvd.movies.api.dto.Rating;
import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.entities.MovieEntity;
import ch.heigvd.movies.entities.RatingEntity;
import ch.heigvd.movies.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMoviesService {

    List<Movie> getAllMovies(String keyword, String page);

    User decodeJWT(String jwt);

    User getUserByEmail(String email);

    String getUserEmailByRating(String ratingId);

    List<Rating> getRatingsByMovie(String movieId);

    List<Rating> getRatingsByUserId(String userId);

    void addRating(String userId, int movieId, int rating, String description);

    void updateRating(String ratingId, int newRating, String newDescription);

    void removeRating(String ratingId);

    double getAvgRatingByMovie(String movieId);

    MovieEntity toMovieEntity(Movie movie);

    Movie toMovie(MovieEntity movieEntity);

    RatingEntity toRatingEntity(Rating rating);

    Rating toRating(RatingEntity ratingEntity);

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
