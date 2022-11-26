package com.co.talata.api;
import com.co.talata.model.movie.Movie;
import com.co.talata.usecase.getallmovie.GetAllMovieUseCase;
import com.co.talata.usecase.getmoviebyid.GetMovieByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
private final GetAllMovieUseCase getAllMovieUseCase;

private final GetMovieByIdUseCase getMovieByIdUseCase;

    @GetMapping(path = "/movies")
    public List<Movie> popularMovies() throws IOException {
        return getAllMovieUseCase.getAllPopularMovies(1);
    }

    @GetMapping(path = "/movies/{id}")
    public Movie getMovieByID(@PathVariable(required = true) int id, @RequestParam(defaultValue = "1") int page) throws IOException {
        return getMovieByIdUseCase.getMovieById(page, id);
    }
}
