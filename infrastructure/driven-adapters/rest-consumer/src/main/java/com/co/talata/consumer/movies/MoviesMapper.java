package com.co.talata.consumer.movies;

import com.co.talata.model.movie.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class MoviesMapper {
    private final ObjectMapper mapper;

    public List<MoviesResponse> mapJSONArrayToMovieResponse(JSONArray Jarray) {
        return StreamSupport.stream(Jarray.spliterator(), false).map(m -> {
            try {
                return mapper.readValue(m.toString(), MoviesResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public Movie movieResponseToMovie(MoviesResponse dto) {
        return Movie.builder()
                .adult(dto.adult)
                .backdrop_path(dto.backdrop_path)
                .genre_ids(dto.genre_ids)
                .id(dto.id)
                .original_language(dto.original_language)
                .original_title(dto.original_title)
                .overview(dto.overview)
                .popularity(dto.popularity)
                .poster_path(dto.poster_path)
                .release_date(dto.release_date)
                .title(dto.title)
                .video(dto.video)
                .vote_average(dto.vote_average)
                .vote_count(dto.vote_count)
                .build();
    }

    public List<Movie> moviesResponseListToMovieList(List<MoviesResponse> movies){
        return movies.stream().map(this::movieResponseToMovie).collect(Collectors.toList());
    }
}
