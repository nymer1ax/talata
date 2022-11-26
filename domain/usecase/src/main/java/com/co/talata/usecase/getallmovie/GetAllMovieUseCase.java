package com.co.talata.usecase.getallmovie;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class GetAllMovieUseCase  {
   private final MovieRepository movieRepository;

   public List<Movie> getAllPopularMovies(int page) throws IOException {
       return movieRepository.getAllPopular(page);
   }
}
