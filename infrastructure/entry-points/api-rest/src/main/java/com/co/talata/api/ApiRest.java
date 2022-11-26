package com.co.talata.api;
import com.co.talata.model.movie.Movie;
import com.co.talata.usecase.deleteratingmovie.DeleteratingmovieUseCase;
import com.co.talata.usecase.exceptions.Response;
import com.co.talata.usecase.getallmovie.GetAllMovieUseCase;
import com.co.talata.usecase.getmoviebyid.GetMovieByIdUseCase;
import com.co.talata.usecase.ratemovie.RatemovieUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
public class ApiRest {
private final GetAllMovieUseCase getAllMovieUseCase;

private final GetMovieByIdUseCase getMovieByIdUseCase;

private final RatemovieUseCase ratemovieUseCase;

private final DeleteratingmovieUseCase deleteratingmovieUseCase;

    @GetMapping(path = "/movies")
    public ResponseEntity<Response> popularMovies(@RequestParam(value = "1") int page, @RequestParam(value = "1") int paginationNumPage, @RequestParam(value = "20") int paginationSizeElements  ) throws IOException {

        List<Movie> movies =  getAllMovieUseCase.getAllPopularMovies(page);

        Page<Movie> pageList = new PageImpl<>(movies, PageRequest.of(paginationNumPage, paginationSizeElements), movies.size());

        var response = Response.builder().codigoResultado("200").descripcionRespuesta("Exitosa").fecha(LocalDateTime.now())
                .result(pageList).build();

        return  ResponseEntity.status(HttpStatus.OK).body(response);
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
