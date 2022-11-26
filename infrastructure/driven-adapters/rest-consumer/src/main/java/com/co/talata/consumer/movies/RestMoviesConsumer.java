package com.co.talata.consumer.movies;

import com.co.talata.consumer.movies.exceptions.custom.NoDataFoundException;
import com.co.talata.consumer.movies.exceptions.custom.RequestNotSuccesfulException;
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

    private final MoviesURL moviesURL;

    @Override
    public List<Movie> findAllTopRated(int page) throws IOException {


        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder().addPathSegment("movie")
                .addPathSegment("top_rated").addQueryParameter("page", String.valueOf(page)).build();

        Request request = moviesURL.generateRequest(httpUrl);

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()){
            throw new RequestNotSuccesfulException("La operación no ha sido exitosa. No pudo obtener los datos");
        }

        String jsonData = response.body().string();

        if(jsonData == null){
            throw new NoDataFoundException("No se han encontrado datos para esta consulta.");
        }

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("results");

        var movies = moviesMapper.mapJSONArrayToMovieResponse(jsonArray);

        return moviesMapper.moviesResponseListToMovieList(movies);

    }

    @Override
    public Movie findById(int page, int id) throws IOException {

        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(id))
                .addQueryParameter("page", String.valueOf(page)).build();

        Request request = moviesURL.generateRequest(httpUrl);

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()){
            throw new RequestNotSuccesfulException("La operación no ha sido exitosa. No pudo obtener los datos");
        }

        String jsonData = response.body().string();

        if(jsonData == null){
            throw new NoDataFoundException("No se han encontrado datos para esta consulta.");
        }

        JSONObject jsonObject = new JSONObject(jsonData);

        MoviesResponse moviesResponse = moviesMapper.JSONOBJECTtoMovieResponse(jsonObject);

        return moviesMapper.movieResponseToMovie(moviesResponse);

    }
}
