package com.zinemaapp.zinemaapp.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TmdbFilmResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("overview")
    private String synopsis;
    @JsonProperty("poster_path")
    private String poster;
    @JsonProperty("vote_average")
    private double rating;
    private List<TmdbGenre> genres;
    private TmdbCredits credits;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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
