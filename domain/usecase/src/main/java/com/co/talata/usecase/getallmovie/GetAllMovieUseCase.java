package com.co.talata.usecase.getallmovie;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.co.talata.usecase.exceptions.custom.CustomException;
import com.co.talata.usecase.exceptions.custom.MovieNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class GetAllMovieUseCase  {
   private final MovieRepository movieRepository;

   public List<Movie> getAllPopularMovies(int page) throws IOException {

       List<Movie> movies = movieRepository.findAllTopRated(page);

       if(page>=500){
           throw new CustomException("La pagina a elegir debe ser menor o igual a 500.");
       }

       if(movies.isEmpty()){
           throw new MovieNotFoundException("No se han encontrado peliculas.");
       }

       return movies;
   }
}
