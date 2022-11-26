package com.co.talata.usecase.getmoviebyid;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class GetMovieByIdUseCase {
    private final MovieRepository movieRepository;

    public Movie getMovieById(int page, int id) throws IOException {
        return movieRepository.findById(page, id);
    }
}
