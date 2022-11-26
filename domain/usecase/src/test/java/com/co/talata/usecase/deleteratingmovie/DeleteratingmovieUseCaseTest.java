package com.co.talata.usecase.deleteratingmovie;

import com.co.talata.model.movie.Rating;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.model.movie.gateways.RatingMovieRepository;
import com.co.talata.usecase.exceptions.custom.CustomException;
import com.co.talata.usecase.exceptions.custom.UnauthorizedException;
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

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DeleteratingmovieUseCaseTest {
    @Mock private MovieRepository movieRepository;

    @Mock private RatingMovieRepository ratingMovieRepository;

    @InjectMocks private DeleteratingmovieUseCase deleteratingmovieUseCase;

    @Test
    public void status7() throws IOException {
        int movieid = 1;
        String gues = "1111";

        Rating rating = Rating.builder().status_code(7).build();

        Mockito.when(ratingMovieRepository.delete(movieid, gues)).thenReturn(rating);

        Exception ex = Assertions.assertThrows(
                UnauthorizedException.class,
                () -> {
                    deleteratingmovieUseCase.eliminarRating(movieid, gues);
                });
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("Unauthorized, verifica tu guess Session Id.", ex.getMessage());

    }




}