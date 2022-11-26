package com.co.talata.api;
import com.co.talata.model.movie.Movie;
import com.co.talata.usecase.getallmovie.GetAllMovieUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
//    private final MyUseCase useCase;
private final GetAllMovieUseCase getAllMovieUseCase;

    @GetMapping(path = "/movies/popular")
    public List<Movie> popularMovies() throws IOException {
        return getAllMovieUseCase.getAllPopularMovies(1);
    }
}
