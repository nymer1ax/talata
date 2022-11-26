package com.co.talata.api;
import com.co.talata.model.movie.Movie;
import com.co.talata.usecase.deleteratingmovie.DeleteratingmovieUseCase;
import com.co.talata.usecase.getallmovie.GetAllMovieUseCase;
import com.co.talata.usecase.getmoviebyid.GetMovieByIdUseCase;
import com.co.talata.usecase.ratemovie.RatemovieUseCase;
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

private final RatemovieUseCase ratemovieUseCase;

private final DeleteratingmovieUseCase deleteratingmovieUseCase;

    @GetMapping(path = "/movies")
    public List<Movie> popularMovies() throws IOException {
        return getAllMovieUseCase.getAllPopularMovies(1);
    }

    @GetMapping(path = "/movies/{id}")
    public Movie getMovieByID(@PathVariable(required = true) int id) throws IOException {
        return getMovieByIdUseCase.getMovieById(id);
    }

    @PostMapping(path = "/movies/{id}/rating")
    public Movie rateMovie(@PathVariable(required = true) int id, @RequestParam(defaultValue = "8f1529e1ab26cf1926f9c934ca239df7") String guestSessionId, @RequestBody String value) throws IOException {
        return ratemovieUseCase.RateMovie(id, guestSessionId, value);
    }

    @DeleteMapping(path = "/movies/{id}/rating")
    public Movie getMovieByID(@PathVariable(required = true) int id, @RequestParam(defaultValue = "8f1529e1ab26cf1926f9c934ca239df7") String guestSessionId) throws IOException {
        return deleteratingmovieUseCase.eliminarRating(id, guestSessionId);
    }
}
