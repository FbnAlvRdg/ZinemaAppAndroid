package com.zinemaapp.zinemaapp.dto.internal;

import com.zinemaapp.zinemaapp.dto.external.TmdbCredits;


import java.time.LocalDate;
import java.util.List;

public class TvSerieDTO {
    private int id;
    private String name;
    private List<String> originCountry;
    private String overview;
    private String poster;
    private Double rating;
    private LocalDate firstAireDate;
    private List<GenreDTO> genres;
    private List<ActorDTO> actors;

    public TvSerieDTO(int id, String name, List<String> originCountry, String overview, String poster, Double rating, LocalDate firstAireDate) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.overview = overview;
        this.poster = poster;
        this.rating = rating;
        this.firstAireDate = firstAireDate;
    }

    public TvSerieDTO(int id, String name, List<String> originCountry, String overview, String poster, Double rating, LocalDate firstAireDate, List<GenreDTO> genres, List<ActorDTO> actors) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.overview = overview;
        this.poster = poster;
        this.rating = rating;
        this.firstAireDate = firstAireDate;
        this.genres = genres;
        this.actors = actors;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public TvSerieDTO(Double rating) {
        this.rating = rating;
    }

    public LocalDate getFirstAireDate() {
        return firstAireDate;
    }

    public void setFirstAireDate(LocalDate firstAireDate) {
        this.firstAireDate = firstAireDate;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<ActorDTO> getActors() {
        return actors;
    }

    public void setActors(List<ActorDTO> actors) {
        this.actors = actors;
    }
}
