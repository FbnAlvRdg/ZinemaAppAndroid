package com.zinemaapp.zinemaapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class FilmDTO {
    private int id;
    private String title;
    private String originalTitle;
    private LocalDate releaseDate;
    private String synopsis;
    private String poster;
    private double rating;

    public FilmDTO() {
    }

    public FilmDTO(int id, String title, String originalTitle, LocalDate releaseDate, String synopsis, String poster, double rating) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.poster = poster;
        this.rating = rating;
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
}
