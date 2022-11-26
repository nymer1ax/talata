package com.co.talata.usecase.getmoviebyid;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.usecase.exceptions.custom.CustomException;
import com.co.talata.usecase.exceptions.custom.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.Optional;


@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetMovieByIdUseCaseTest {

    @Mock private MovieRepository movieRepository;

    @InjectMocks private GetMovieByIdUseCase  getMovieByIdUseCase;

    @Test
    public void noexistelapelicula() throws IOException {
        int id = 10;
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(
                MovieNotFoundException.class,
                () -> {
                    getMovieByIdUseCase.getMovieById(id);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("No se ha encontrado la pelicula!", ex.getMessage());

    }

    @Test
    public void existepelicula() throws IOException {
        Optional<Movie> movie = Optional.of(Movie.builder().id(12).title("spiderman").build());
        Mockito.when(movieRepository.findById(movie.get().id)).thenReturn(movie);
        var respuesta = getMovieByIdUseCase.getMovieById(movie.get().id);
        Assertions.assertEquals(movie.get().id,respuesta.id );
    }

}