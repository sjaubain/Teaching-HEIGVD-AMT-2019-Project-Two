package ch.heigvd.movies.services;

import ch.heigvd.movies.api.dto.Movie;
import ch.heigvd.movies.api.dto.Rating;
import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.entities.MovieEntity;
import ch.heigvd.movies.entities.RatingEntity;
import ch.heigvd.movies.entities.UserEntity;

import java.util.List;

public interface IMoviesService {

    List<Movie> getAllMovies(String keyword);

    User decodeJWT(String jwt);

    User getUserByEmail(String email);

    List<Rating> getRatingsByMovie(String movieId);

    void addRating(String userId, int movieId, int rating, String description);

    MovieEntity toMovieEntity(Movie movie);

    Movie toMovie(MovieEntity movieEntity);

    RatingEntity toRatingEntity(Rating rating);

    Rating toRating(RatingEntity ratingEntity);

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
