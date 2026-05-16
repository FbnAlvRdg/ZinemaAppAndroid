package com.zinemaapp.zinemaapp.dto.external.tvserie;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zinemaapp.zinemaapp.dto.external.TmdbCredits;
import com.zinemaapp.zinemaapp.dto.external.TmdbGenre;
import com.zinemaapp.zinemaapp.dto.internal.GenreDTO;

import java.util.List;

public class TmdbTvSerieResponse {
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("origin_country")
    private List<String> originCountry;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("poster_path")
    private String poster;
    @JsonProperty("vote_average")
    private Double rating;
    @JsonProperty("first_air_date")
    private String firstAireDate;
    @JsonProperty("genres")
    private List<TmdbGenre> genres;
    @JsonProperty("credits")
    private TmdbCredits credits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFirstAireDate() {
        return firstAireDate;
    }

    public void setFirstAireDate(String firstAireDate) {
        this.firstAireDate = firstAireDate;
    }

    public List<TmdbGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<TmdbGenre> genres) {
        this.genres = genres;
    }

    public TmdbCredits getCredits() {
        return credits;
    }

    public void setCredits(TmdbCredits credits) {
        this.credits = credits;
    }
}
