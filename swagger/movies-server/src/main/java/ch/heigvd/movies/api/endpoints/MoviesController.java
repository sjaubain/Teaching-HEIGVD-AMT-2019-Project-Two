package ch.heigvd.movies.api.endpoints;

import ch.heigvd.movies.api.MoviesApi;
import ch.heigvd.movies.api.dto.Movie;
import ch.heigvd.movies.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MoviesController implements MoviesApi {

    @Autowired
    private MoviesService moviesService;

    @Override
    public ResponseEntity<List<Movie>> getMovies() {

        List<Movie> ret = moviesService.getAllMovies();

        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }
}
