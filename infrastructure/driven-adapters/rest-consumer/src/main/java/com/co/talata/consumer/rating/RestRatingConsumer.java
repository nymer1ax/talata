package com.co.talata.consumer.rating;

import com.co.talata.consumer.MoviesURL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper;

    public RatingResponse createRate(int movieId, String guestSessionId, String value) throws IOException {

        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(movieId))
                .addPathSegment("rating")
                .addQueryParameter("guest_session_id", guestSessionId).build();


        RequestBody requestBody = RequestBody.create(value, MediaType.parse("application/json"));

        Request request = moviesURL.generateRequest(httpUrl).newBuilder().post(requestBody).build();

        Response response = client.newCall(request).execute();

        if(response.code() == 400){
            throw new RuntimeException("Ingresaste algo mal, verifica.");
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
