package ch.heigvd.movies.services;

import ch.heigvd.movies.api.dto.Movie;
import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.entities.MovieEntity;
import ch.heigvd.movies.entities.UserEntity;

import java.util.List;

public interface IMoviesService {

    List<Movie> getAllMovies();

    User decodeJWT(String jwt);

    MovieEntity toMovieEntity(Movie movie);

    Movie toMovie(MovieEntity movieEntity);

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
