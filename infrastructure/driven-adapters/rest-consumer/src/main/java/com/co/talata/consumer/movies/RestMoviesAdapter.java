package com.co.talata.consumer.movies;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RestMoviesAdapter implements MovieRepository {

   private final RestMoviesConsumer restMoviesConsumer;
   private final MoviesMapper moviesMapper;
    @Override
    public List<Movie> findAllTopRated(int page) throws IOException {
        List<MoviesResponse> movies = restMoviesConsumer.findAllTopRated(page);
        return moviesMapper.moviesResponseListToMovieList(movies);
    }

    @Override
    public Optional<Movie> findById(int id) throws IOException {
        Optional<MoviesResponse> movie = restMoviesConsumer.findById(id);
        return Optional.of(moviesMapper.movieResponseToMovie(movie.get()));
    }
}
