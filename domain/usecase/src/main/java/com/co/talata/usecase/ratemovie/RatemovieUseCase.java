package com.co.talata.usecase.ratemovie;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.Rating;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.model.movie.gateways.RatingMovieRepository;
import com.co.talata.usecase.exceptions.custom.CustomException;
import com.co.talata.usecase.exceptions.custom.MovieNotFoundException;
import com.co.talata.usecase.exceptions.custom.UnauthorizedException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class RatemovieUseCase {

    private final RatingMovieRepository ratingMovieRepository;
    private final MovieRepository movieRepository;

    public Movie RateMovie(int movieId, String guessSessionId, String value) throws IOException{

        Rating rating = ratingMovieRepository.rate(movieId, guessSessionId, value);

        if(rating.getStatus_code() == 7){
            throw new UnauthorizedException("Unauthorized, verifica tu guess Session Id.");
        }

        if(rating.getStatus_code() == 404){
            throw new MovieNotFoundException("Lo siento mucho! Esta pelicula no ha sido encontrada.");
        }

        Optional<Movie> movie = movieRepository.findById(movieId);

        if(!movie.isPresent()){
            throw new MovieNotFoundException("Lo siento mucho! Esta pelicula no ha sido encontrada.");
        }

        return movie.get();


    }
}
