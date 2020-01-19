package ch.heigvd.movies.services;

import ch.heigvd.movies.api.dto.Movie;
import ch.heigvd.movies.api.dto.Rating;
import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.entities.MovieEntity;
import ch.heigvd.movies.entities.RatingEntity;
import ch.heigvd.movies.entities.UserEntity;
import ch.heigvd.movies.repositories.MoviesRepository;
import ch.heigvd.movies.repositories.RatingsRepository;
import ch.heigvd.movies.repositories.UsersRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is the 'main' services class
 * that regroups all types of services (user, rating
 * and movie)
 */
@Service
public class MoviesService implements IMoviesService {

    //TODO: share it with the second API
    private static final String SECRET_KEY = "secret";
    public static final int PAGE_SIZE = 10;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RatingsRepository ratingsRepository;

    /**
     * retrieve movies with description containing
     * the keyword, if specified.
     * @param keyword query keyword
     * @return the list of retrieved movies
     */
    @Override
    public List<Movie> getAllMovies(String keyword, String page) {

        Pageable pageable = PageRequest.of(Integer.valueOf(page), PAGE_SIZE);

        List<Movie> ret = new LinkedList<>();
        Iterable<MovieEntity> allMovies = moviesRepository.findAll(pageable);
        for(MovieEntity movieEntity : allMovies) {
            if(keyword != "" && keyword != null) {

                // if a keyword is specified
                if(movieEntity.getDescription().contains(keyword)) {
                    ret.add(toMovie(movieEntity));
                }

            } else {
                // else return all retrieved movies
                ret.add(toMovie(movieEntity));
            }
        }
        return ret;

    }

    /**
     * Decode the given JWT and check it
     * Used to know if a user is authenticated
     * to navigate through movies and perform
     * CRUD actions
     * @param jwt the jwt
     * @return the User corresponding
     * to the jwt
     */
    @Override
    public User decodeJWT(String jwt) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        try {

            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT tokenJWT = verifier.verify(jwt);

            // Add the user in the DB if not already registered
            User user = new User().email(tokenJWT.getClaim("email").asString());
            if(!usersRepository.existsById(user.getEmail())) {
                usersRepository.save(new UserEntity(user.getEmail()));
            }
            return user;

        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {

        try {
            return toUser(usersRepository.findById(email).get());
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public String getUserEmailByRating(String ratingId) {

        try {
            RatingEntity ratingEntity = ratingsRepository.findById(Integer.valueOf(ratingId)).get();
            return ratingEntity.getUserId();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Rating> getRatingsByMovie(String movieId) {

        try {

            List<Rating> ret = new LinkedList<>();
            Iterable<RatingEntity> ratings = ratingsRepository.getRatingsByMovieId(Integer.valueOf(movieId));
            for(RatingEntity ratingEntity : ratings) {
                ret.add(toRating(ratingEntity));
            }
            return ret;

        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {

        try {

            List<Rating> ret = new LinkedList<>();
            Iterable<RatingEntity> ratings = ratingsRepository.getRatingsByUserId(userId);
            for(RatingEntity ratingEntity : ratings) {
                ret.add(toRating(ratingEntity));
            }
            return ret;

        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public void addRating(String userId, int movieId, int rating, String description) {
        ratingsRepository.save(new RatingEntity(userId, movieId, rating, description));
    }

    @Override
    public void updateRating(String ratingId, int newRating, String newDescription) {
        ratingsRepository.updateRating(Integer.valueOf(ratingId), newRating, newDescription);
    }

    @Override
    public void removeRating(String ratingId) {
        ratingsRepository.delete(ratingsRepository.findById(Integer.valueOf(ratingId)).get());
    }

    @Override
    public double getAvgRatingByMovie(String movieId) {
        return 0;
    }

    @Override
    public MovieEntity toMovieEntity(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setMovieId(movie.getMovieId());
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setDescription(movie.getDescription());
        movieEntity.setDirector(movie.getDirector());
        return movieEntity;
    }

    @Override
    public Movie toMovie(MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setMovieId(movieEntity.getMovieId());
        movie.setTitle(movieEntity.getTitle());
        movie.setDescription(movieEntity.getDescription());
        movie.setDirector(movieEntity.getDirector());
        return movie;
    }

    @Override
    public RatingEntity toRatingEntity(Rating rating) {
        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setDescription(rating.getDescription());
        ratingEntity.setRating(rating.getRating());
        ratingEntity.setMovieId(rating.getRatingId());
        return ratingEntity;
    }

    @Override
    public Rating toRating(RatingEntity ratingEntity) {
        Rating rating = new Rating();
        rating.setDescription(ratingEntity.getDescription());
        rating.setRating(ratingEntity.getRating());
        rating.setRatingId(ratingEntity.getRatingId());
        return rating;
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
