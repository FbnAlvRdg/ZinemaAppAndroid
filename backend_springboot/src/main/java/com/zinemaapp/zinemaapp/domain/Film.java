package com.zinemaapp.zinemaapp.domain;

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
    private List<Person> actors;
    private Person director;
    private List<Genre> genres;
}
