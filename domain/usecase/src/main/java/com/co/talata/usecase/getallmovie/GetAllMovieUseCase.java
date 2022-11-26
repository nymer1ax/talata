package com.co.talata.usecase.getallmovie;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class GetAllMovieUseCase  {
   private final MovieRepository movieRepository;

   public List<Movie> getAllPopularMovies(int page) throws IOException {

       //El maximo de paginas son 500
       if(page>500){
           page = 499;
       }

       List<Movie> movies = movieRepository.findAllTopRated(page);

       if(movies.isEmpty()){
           throw new RuntimeException("No se han encontrado peliculas.");
       }

       return movieRepository.findAllTopRated(page);
   }
}
