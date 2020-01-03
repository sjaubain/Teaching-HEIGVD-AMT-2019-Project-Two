package ch.heigvd.movies.services;

import ch.heigvd.movies.api.dto.Movie;
import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.entities.MovieEntity;
import ch.heigvd.movies.entities.UserEntity;
import ch.heigvd.movies.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MoviesService implements IMoviesService {

    //TODO: share it with the second API
    private static final String SECRET_KEY = "secret";

    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public List<Movie> getAllMovies() {

        List<Movie> ret = new LinkedList<>();
        Iterable<MovieEntity> allMovies = moviesRepository.findAll();
        for(MovieEntity movieEntity : allMovies) {
            ret.add(toMovie(movieEntity));
        }
        return ret;
    }


    /**
     * Decode the given JWT and check it
     * @param jwt the jwt
     * @return the User corresponding
     * to the jwt
     */
    @Override
    public User decodeJWT(String jwt) {

       return null;
    }

    @Override
    public MovieEntity toMovieEntity(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setMovie_id(movie.getMovieId());
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setDescription(movie.getDescription());
        movieEntity.setDirector(movie.getDirector());
        return movieEntity;
    }

    @Override
    public Movie toMovie(MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setMovieId(movieEntity.getMovie_id());
        movie.setTitle(movieEntity.getTitle());
        movie.setDescription(movieEntity.getDescription());
        movie.setDirector(movieEntity.getDirector());
        return movie;
    }

    /**
     * Convert DTO to Entity
     * @param user the user DTO
     * @return the user entity
     */
    public UserEntity toUserEntity(User user) {
        //TODO: use a mapper would be cleaner
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

    /**
     * Convert Entity to DTO
     * @param userEntity the user entity
     * @return the user DTO
     */
    public User toUser(UserEntity userEntity) {
        //TODO: use a mapper would be cleaner
        User user = new User();
        user.setEmail(userEntity.getEmail());
        return user;
    }
}
