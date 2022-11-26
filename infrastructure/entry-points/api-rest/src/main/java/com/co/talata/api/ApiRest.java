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
import org.springframework.validation.annotation.Validated;
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
@Validated
public class ApiRest {
private final GetAllMovieUseCase getAllMovieUseCase;

private final GetMovieByIdUseCase getMovieByIdUseCase;

private final RatemovieUseCase ratemovieUseCase;

private final DeleteratingmovieUseCase deleteratingmovieUseCase;

    @GetMapping(path = "/movies")
    public ResponseEntity<Response> popularMovies(@RequestParam(defaultValue = "2", required = true) int page, @RequestParam(defaultValue = "0") int pagePagination, @RequestParam(defaultValue = "20") int size) throws IOException {

        List<Movie> movies =  getAllMovieUseCase.getAllPopularMovies(page);

        Page<Movie> pageList = new PageImpl<>(movies, PageRequest.of(pagePagination, size), movies.size());

        Response response = Response.builder().codigoResultado("200").descripcionRespuesta("Este es el listado de las peliculas más populares ahora mismo: ").fecha(LocalDateTime.now().toString())
                .result(pageList).build();

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/movies/{id}")
    public ResponseEntity<Response> getMovieByID(@PathVariable(required = true) int id) throws IOException {

       Movie movies = getMovieByIdUseCase.getMovieById(id);

        Response response = Response.builder().codigoResultado("202").descripcionRespuesta("Se ha encontrado con exito la pelicula: "+id).fecha(LocalDateTime.now().toString())
                .result(movies).build();

        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping(path = "/movies/{id}/rating")
    public ResponseEntity<Response> rateMovie(@PathVariable(required = true) int id, @RequestParam(defaultValue = "8f1529e1ab26cf1926f9c934ca239df7") String guestSessionId, @RequestBody String value) throws IOException {

        Movie rate = ratemovieUseCase.RateMovie(id, guestSessionId, value);

        Response response = Response.builder().codigoResultado("201").descripcionRespuesta("Se ha creado con éxito el rating para la pelicula "+id).fecha(LocalDateTime.now().toString())
                .result(rate).build();

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(path = "/movies/{id}/rating")
    public ResponseEntity<Response> getMovieByID(@PathVariable(required = true) int id, @RequestParam(defaultValue = "8f1529e1ab26cf1926f9c934ca239df7") String guestSessionId) throws IOException {

        Movie rate = deleteratingmovieUseCase.eliminarRating(id, guestSessionId);

        Response response = Response.builder().codigoResultado("201").descripcionRespuesta("se ha eliminado con exito el rating para la pelicula: "+ id).fecha(LocalDateTime.now().toString())
                .result(rate).build();

        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

}
