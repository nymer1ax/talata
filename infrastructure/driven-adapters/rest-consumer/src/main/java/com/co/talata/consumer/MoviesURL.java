package com.co.talata.consumer;

import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MoviesURL {
    @Value("${adapter.restconsumer.url}")
    private String base;

    @Value("${adapter.restconsumer.apikey}")
    private String apikey;

    public HttpUrl generateUrl(){
      return  new HttpUrl.Builder()
                .scheme(base.substring(0, 5))
                .host(base.substring(8, 26))
                .addPathSegment(base.substring(27))
                .addQueryParameter("api_key", apikey).build();
    }

    public Request generateRequest(HttpUrl httpUrl){
        return new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url(httpUrl)
                .build();
    }
}
