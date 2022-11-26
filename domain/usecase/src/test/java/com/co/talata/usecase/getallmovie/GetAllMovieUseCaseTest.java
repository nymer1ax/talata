package com.co.talata.usecase.getallmovie;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.usecase.exceptions.custom.CustomException;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetAllMovieUseCaseTest {

    @Mock private MovieRepository movieRepository;

    @InjectMocks private GetAllMovieUseCase getAllMovieUseCase;

    @Test
    public void getPageMayor500() throws IOException {
        int page = 5000;

        Mockito.when(movieRepository.findAllTopRated(page)).thenReturn(Collections.emptyList());

        Exception ex = Assertions.assertThrows(
                CustomException.class,
                () -> {
                    getAllMovieUseCase.getAllPopularMovies(page);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("La pagina a elegir debe ser menor o igual a 500.", ex.getMessage());

    }


    @Test
    public void getmovies() throws IOException {
        Movie m1 = Movie.builder().title("SPIDERMAN").build();
        Movie m2 = Movie.builder().title("SUPERMAN").build();
        List<Movie> movies = List.of(m1,m2);

        int page = 2;
        Mockito.when(movieRepository.findAllTopRated(page)).thenReturn(movies);
        List<Movie> respuesta = getAllMovieUseCase.getAllPopularMovies(page);
        Assertions.assertEquals(respuesta.size(), movies.size());
    }

}