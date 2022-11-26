package com.co.talata.consumer.movies;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@Log
@Service
@RequiredArgsConstructor
public class RestMoviesConsumer implements MovieRepository {

    @Value("${adapter.restconsumer.url}")
    private String base;

    @Value("${adapter.restconsumer.apikey}")
    private String apikey;

    private final OkHttpClient client;

    private final ObjectMapper mapper;

    private final MoviesMapper moviesMapper;

    @Override
    public List<Movie> getAllPopular(int page) throws IOException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(base.substring(0, 5))
                .host(base.substring(8, 26))
                .addPathSegment(base.substring(27))
                .addPathSegment("movie")
                .addPathSegment("top_rated")
                .addQueryParameter("api_key", apikey)
                .addQueryParameter("page", String.valueOf(page))
                .build();

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url(httpUrl)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()){
            throw new RuntimeException("No se ha podido obtener los datos");
        }

        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("results");

        var movies = moviesMapper.mapJSONArrayToMovieResponse(jsonArray);

        return moviesMapper.moviesResponseListToMovieList(movies);

    }
}
