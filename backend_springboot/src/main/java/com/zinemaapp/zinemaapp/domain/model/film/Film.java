package com.zinemaapp.zinemaapp.domain.model.film;

import com.zinemaapp.zinemaapp.domain.model.common.Actor;
import com.zinemaapp.zinemaapp.domain.model.common.Genre;

import java.time.LocalDate;
import java.util.List;

public class Film {
    private int id;
    private String title;
    private String originalTitle;
    private LocalDate releaseDate;
    private String synopsis;
    private String poster;
    private double rating;
    private List<Actor> actors;
    private String director;
    private List<Genre> genres;

    public Film(int id, String title, String originalTitle, LocalDate releaseDate, String synopsis, String poster, double rating, List<Actor> actors, String director, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.poster = poster;
        this.rating = rating;
        this.actors = actors;
        this.director = director;
        this.genres = genres;
    }

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
