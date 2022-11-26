package com.co.talata.consumer.rating;

import com.co.talata.model.movie.Rating;
import com.co.talata.model.movie.gateways.RatingMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class RestRatingAdapter implements RatingMovieRepository {

    private final RestRatingConsumer restRatingConsumer;
    private final RatingMapper ratingMapper;

    @Override
    public Rating rate(int movieId, String guestSessionId, String value) throws IOException {
        RatingResponse rating = restRatingConsumer.createRate(movieId, guestSessionId, value);
        return ratingMapper.ratingResponseToRating(rating);
    }


}
