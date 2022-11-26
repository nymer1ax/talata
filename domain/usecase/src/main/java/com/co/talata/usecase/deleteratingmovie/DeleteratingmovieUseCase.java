package com.co.talata.usecase.deleteratingmovie;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.Rating;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.model.movie.gateways.RatingMovieRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class DeleteratingmovieUseCase {

    private final RatingMovieRepository ratingMovieRepository;
    private final MovieRepository movieRepository;

    public Movie eliminarRating(int movieId, String guessSessionId) throws IOException {

        Rating rating = ratingMovieRepository.delete(movieId, guessSessionId);

        if(rating.getStatus_code() == 7){
            throw new RuntimeException("Unauthorized, verifica tu guess Session Id.");
        }

        if(rating.getStatus_code() == 404) {
            throw new RuntimeException("Lo siento mucho! Esta pelicula no ha sido encontrada.");
        }

        var movie = movieRepository.findById(movieId);

        if(!movie.isPresent()){
            throw new RuntimeException("Lo siento mucho! Esta pelicula no ha sido encontrada.");
        }

        return movie.get();

    }
}
