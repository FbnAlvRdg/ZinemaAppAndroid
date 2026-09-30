package com.zinemaapp.zinemaapp.domain.model;

import java.time.LocalDate;
import java.util.List;

public class TvSerie {
    private int id;
    private String name;
    private List<String> originCountry;
    private String overview;
    private String poster;
    private Double rating;
    private LocalDate firstAireDate;
    private List<Genre> genres;
    private List<Actor> actors;

    public TvSerie(int id, String name, List<String> originCountry, String overview, String poster, List<Actor> actors, List<Genre> genres, LocalDate firstAireDate, Double rating) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.overview = overview;
        this.poster = poster;
        this.actors = actors;
        this.genres = genres;
        this.firstAireDate = firstAireDate;
        this.rating = rating;
    }

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

    public LocalDate getFirstAireDate() {
        return firstAireDate;
    }

    public void setFirstAireDate(LocalDate firstAireDate) {
        this.firstAireDate = firstAireDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
