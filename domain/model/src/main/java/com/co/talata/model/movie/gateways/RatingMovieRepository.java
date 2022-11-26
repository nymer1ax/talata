package com.co.talata.model.movie.gateways;

import com.co.talata.model.movie.Movie;
import com.co.talata.model.movie.Rating;

import java.io.IOException;

public interface RatingMovieRepository {
    public Rating rate(int movieId, String guestSessionId, String value) throws IOException;

    public Rating delete(int movieId, String guestSessionId) throws IOException;

}
