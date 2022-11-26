package com.co.talata.usecase.getmoviebyid;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.usecase.exceptions.custom.MovieNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class GetMovieByIdUseCase {
    private final MovieRepository movieRepository;

    public Movie getMovieById(int id) throws IOException {

        Optional<Movie> movie =  movieRepository.findById(id);

        if(!movie.isPresent()){
            throw new MovieNotFoundException("No se ha encontrado la pelicula!");
        }

        return movie.get();
    }
}
