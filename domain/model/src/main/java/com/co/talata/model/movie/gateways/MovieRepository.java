package com.co.talata.model.movie.gateways;

import com.co.talata.model.movie.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> findAllTopRated(int page) throws IOException;
    Optional<Movie> findById(int id) throws IOException;
}
