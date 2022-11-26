package com.co.talata.model.movie.gateways;

import com.co.talata.model.movie.Movie;

import java.io.IOException;

public interface RatingMovieRepository {
    public boolean rate(int movieId, int valueRating, String guestSessionId) throws IOException;
    public boolean delete(int movieId, String guestSessionId);
}
