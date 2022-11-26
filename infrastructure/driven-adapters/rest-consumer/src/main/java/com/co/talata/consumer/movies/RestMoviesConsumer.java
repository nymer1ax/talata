package com.co.talata.consumer.movies;

import com.co.talata.consumer.MoviesURL;
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
import java.util.ArrayList;
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

        Request request = moviesURL.generateRequest(httpUrl).newBuilder().get().build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();

        JSONObject jsonObject = new JSONObject(jsonData);

        boolean validacion = jsonObject.has("errors");

        if(validacion){
            return Collections.emptyList();
        }

        JSONArray jsonArray = jsonObject.getJSONArray("results");

        if(jsonArray == null){
            throw new IOException("La consulta ha fallado. ");
        }

        return moviesMapper.mapJSONArrayToMovieResponse(jsonArray);


    }


    public Optional<MoviesResponse> findById(int id) throws IOException {

        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(id))
                .build();

        Request request = moviesURL.generateRequest(httpUrl).newBuilder().get().build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();

        JSONObject jsonObject = new JSONObject(jsonData);

        boolean validacion = jsonObject.has("success");

        if(validacion){
            return Optional.empty();
        }


        return Optional.of(moviesMapper.JSONOBJECTtoMovieResponse(jsonObject));


    }
}
