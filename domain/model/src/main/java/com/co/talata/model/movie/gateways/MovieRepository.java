package com.co.talata.model.movie.gateways;

import com.co.talata.model.movie.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieRepository {
    List<Movie> findAllTopRated(int page) throws IOException;
    Movie findById(int page, int id) throws IOException;
}
