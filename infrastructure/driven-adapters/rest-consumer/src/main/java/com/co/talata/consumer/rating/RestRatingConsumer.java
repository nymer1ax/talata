package com.co.talata.consumer.rating;

import com.co.talata.consumer.MoviesURL;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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


    public RatingResponse rate(int movieId, String guestSessionId) throws IOException {

        HttpUrl httpUrl = moviesURL.generateUrl().newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(movieId))
                .addPathSegment("rating")
                .addQueryParameter("guest_session_id", guestSessionId).build();

        Request request = moviesURL.generateRequest(httpUrl);

        Response response = client.newCall(request).execute();

        String json =  response.body().string();

        JSONObject jsonObject = new JSONObject(json);

        return ratingMapper.jsonObjectToRatingResponse(jsonObject);

    }



}
