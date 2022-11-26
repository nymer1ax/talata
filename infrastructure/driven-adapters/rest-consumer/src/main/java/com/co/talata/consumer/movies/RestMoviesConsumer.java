package com.co.talata.consumer.movies;

import com.co.talata.consumer.MoviesURL;
import com.co.talata.consumer.exceptions.custom.NoDataFoundException;
import com.co.talata.consumer.exceptions.custom.RequestNotSuccesfulException;
import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.gateways.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class RestMoviesConsumer  {
    private final OkHttpClient client;

    private final MoviesMapper moviesMapper;
    private final MoviesURL moviesURL;


    public List<MoviesResponse> findAllTopRated(int page) throws IOException {


        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder().addPathSegment("movie")
                .addPathSegment("top_rated").addQueryParameter("page", String.valueOf(page)).build();

        Request request = moviesURL.generateRequest(httpUrl);

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()){
            throw new RequestNotSuccesfulException("La operación no ha sido exitosa. No pudo obtener los datos");
        }

        String jsonData = response.body().string();

        if(jsonData == null){
            return Collections.emptyList();
        }

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("results");

        return moviesMapper.mapJSONArrayToMovieResponse(jsonArray);


    }


    public Optional<MoviesResponse> findById(int id) throws IOException {

        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(id))
                .build();

        Request request = moviesURL.generateRequest(httpUrl);

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()){
            throw new RequestNotSuccesfulException("La operación no ha sido exitosa. No pudo obtener los datos");
        }

        String jsonData = response.body().string();

        if(jsonData == null){
            return Optional.empty();
        }

        JSONObject jsonObject = new JSONObject(jsonData);

        return Optional.of(moviesMapper.JSONOBJECTtoMovieResponse(jsonObject));


    }
}
