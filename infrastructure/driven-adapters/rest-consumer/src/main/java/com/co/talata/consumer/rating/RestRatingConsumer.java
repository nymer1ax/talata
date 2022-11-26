package com.co.talata.consumer.rating;

import com.co.talata.consumer.MoviesURL;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log
@Service
@RequiredArgsConstructor
public class RestRatingConsumer  {

    private final OkHttpClient client;
    private final RatingMapper ratingMapper;
    private final MoviesURL moviesURL;


    public RatingResponse createRate(int movieId, String guestSessionId, String value) throws IOException {

        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(movieId))
                .addPathSegment("rating")
                .addQueryParameter("guest_session_id", guestSessionId).build();

        RequestBody requestBody = RequestBody.create(value, MediaType.parse("application/json; charset=utf-8"));


        Request request = moviesURL.generateRequest(httpUrl).newBuilder().post(requestBody).build();

        Response response = client.newCall(request).execute();

        if(response.code() == 400){
            throw new RuntimeException("El formato del valor esta incorrecto, verifica.");
        }

        String json =  response.body().string();

        JSONObject jsonObject = new JSONObject(json);

        return ratingMapper.jsonObjectToRatingResponse(jsonObject);

    }

    public RatingResponse deleteRate(int movieId, String guestSessionId) throws IOException {

        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(movieId))
                .addPathSegment("rating")
                .addQueryParameter("guest_session_id", guestSessionId).build();


        Request request = moviesURL.generateRequest(httpUrl).newBuilder().delete().build();

        Response response = client.newCall(request).execute();

        String json =  response.body().string();

        JSONObject jsonObject = new JSONObject(json);

        return ratingMapper.jsonObjectToRatingResponse(jsonObject);

    }



}
