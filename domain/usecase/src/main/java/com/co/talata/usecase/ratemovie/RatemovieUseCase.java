package com.co.talata.usecase.ratemovie;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.model.movie.gateways.RatingMovieRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class RatemovieUseCase {

    private final RatingMovieRepository ratingMovieRepository;
    private final MovieRepository movieRepository;

    public Movie RateMovie(int movieId, String guessSessionId) throws IOException{

        var rating = ratingMovieRepository.rate(movieId, guessSessionId);

        if(rating.getStatus_code() == 7){
            throw new RuntimeException("Unauthorized, verifica tu guess Session Id.");
        }

        if(rating.getStatus_code() == 404){
            throw new RuntimeException("Lo siento mucho! Esta pelicula no ha sido encontrada.");
        }

        Optional<Movie> movie = movieRepository.findById(movieId);

        if(!movie.isPresent()){
            throw new RuntimeException("Lo siento mucho! Esta pelicula no ha sido encontrada.");
        }

        return movie.get();


    }
}
