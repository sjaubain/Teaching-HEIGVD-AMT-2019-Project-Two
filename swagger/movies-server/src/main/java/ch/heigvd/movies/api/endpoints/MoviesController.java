package ch.heigvd.movies.api.endpoints;

import ch.heigvd.movies.api.MoviesApi;
import ch.heigvd.movies.api.dto.Movie;
import ch.heigvd.movies.api.dto.Rating;
import ch.heigvd.movies.api.dto.User;
import ch.heigvd.movies.services.MoviesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MoviesController implements MoviesApi {

    @Autowired
    private MoviesService moviesService;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MoviesController(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<List<Movie>> getMovies(String authorization, String page, String keyword) {

        User askingUser = moviesService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Movie> ret = moviesService.getAllMovies(keyword, page);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getRatingsByMovie(String authorization, String movieId) {

        User askingUser = moviesService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return new ResponseEntity<>(moviesService.getRatingsByMovie(movieId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Rating>> getPersonalRatings(String authorization) {

        User askingUser = moviesService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return new ResponseEntity<>(moviesService.getRatingsByUserId(askingUser.getEmail()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addRating(String authorization, String movieId, Rating rating) {

        User askingUser = moviesService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Get user id (email)
        String userId = askingUser.getEmail();

        moviesService.addRating(userId, Integer.valueOf(movieId),
                Integer.valueOf(rating.getRating()), rating.getDescription());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Object> removeRating(String authorization, String ratingId) {

        User askingUser = moviesService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Check if the user owns the rating before removing it
        if(userOwnsRating(askingUser.getEmail(), ratingId)) {
            moviesService.removeRating(ratingId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Object> updateRating(String authorization, Rating rating, String ratingId) {

        User askingUser = moviesService.decodeJWT(authorization);

        if (askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Check if the user owns the rating before updating it
        if(userOwnsRating(askingUser.getEmail(), ratingId)) {
            moviesService.updateRating(ratingId, rating.getRating(), rating.getDescription());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Object> getAvgRatingByMovie(String authorization, String movieId) {

        User askingUser = moviesService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return null;
    }

    public boolean userOwnsRating(String userId, String ratingId) {
        return moviesService.getUserEmailByRating(ratingId).equals(userId);
    }
}
