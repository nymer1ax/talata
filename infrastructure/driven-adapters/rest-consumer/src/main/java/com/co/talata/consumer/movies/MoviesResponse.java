package com.co.talata.consumer.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesResponse {
    @JsonProperty("adult")
    public Boolean adult;
    @JsonProperty("backdrop_path")
    public String backdrop_path;
    @JsonProperty("genre_ids")
    public List<Integer> genre_ids;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("original_language")
    public String original_language;
    @JsonProperty("original_title")
    public String original_title;
    @JsonProperty("overview")
    public String overview;
    @JsonProperty("popularity")
    public Double popularity;
    @JsonProperty("poster_path")
    public String poster_path;
    @JsonProperty("release_date")
    public String release_date;
    @JsonProperty("title")
    public String title;
    @JsonProperty("video")
    public Boolean video;
    @JsonProperty("vote_average")
    public Double vote_average;
    @JsonProperty("vote_count")
    public Integer vote_count;




}
