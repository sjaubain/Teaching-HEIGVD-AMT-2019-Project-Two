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
    public ResponseEntity<List<Movie>> getMovies(String authorization, String keyword) {

        List<Movie> ret = moviesService.getAllMovies(keyword);

        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
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
    public ResponseEntity<Object> addRating(String authorization, String movieId, String rating, String description) {

        User askingUser = moviesService.decodeJWT(authorization);

        if(askingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Get user id (email)
        String userId = askingUser.getEmail();

        moviesService.addRating(userId, Integer.valueOf(movieId), Integer.valueOf(rating), description);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
