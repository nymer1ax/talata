package com.co.talata.consumer.movies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class MoviesRequest {
    private int i;
}
