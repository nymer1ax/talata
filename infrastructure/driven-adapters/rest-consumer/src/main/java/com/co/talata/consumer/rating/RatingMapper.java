package com.co.talata.consumer.rating;

import com.co.talata.model.movie.Rating;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RatingMapper {

    private final ObjectMapper mapper;

    public RatingResponse jsonObjectToRatingResponse(JSONObject jsonObject) throws JsonProcessingException {
        return mapper.readValue(jsonObject.toString(), RatingResponse.class);
    }

    public Rating ratingResponseToRating(RatingResponse ratingResponse){
        return Rating.builder().status_code(ratingResponse.getStatus_code()).status_message(ratingResponse.getStatus_message()).success(ratingResponse.isSuccess()).build();
    }

}
