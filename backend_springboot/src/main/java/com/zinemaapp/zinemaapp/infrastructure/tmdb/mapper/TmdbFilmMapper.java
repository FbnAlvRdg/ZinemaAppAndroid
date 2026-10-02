package com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper;

import com.zinemaapp.zinemaapp.domain.model.common.Actor;
import com.zinemaapp.zinemaapp.domain.model.film.Film;
import com.zinemaapp.zinemaapp.domain.model.common.Genre;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.credits.TmdbCast;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.credits.TmdbCrew;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.film.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.genre.TmdbGenre;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TmdbFilmMapper {
    public Film toDomain(TmdbFilmResponse tmdbFilmResponse) {

        String director = null;

        if (tmdbFilmResponse.getCredits() != null && tmdbFilmResponse.getCredits().getCrew() != null) {
            for (TmdbCrew crew : tmdbFilmResponse.getCredits().getCrew()) {
                if (crew.getJob() != null && crew.getJob().trim().equalsIgnoreCase("Director")) {
                    director = crew.getName();
                    break;
                }
            }
        }

        int counter = 0;

        List<Actor> actors = new ArrayList<>();
        if (tmdbFilmResponse.getCredits() != null && tmdbFilmResponse.getCredits().getCast() != null) {
            for (TmdbCast cast : tmdbFilmResponse.getCredits().getCast()) {
                if (counter >= 5) {
                    break;
                } else {
                    actors.add(new Actor(cast.getId(), cast.getName(), cast.getCharacter()));
                    counter++;
                }
            }
        }

        List<Genre> genres = new ArrayList<>();

        if (tmdbFilmResponse.getGenres() != null) {
            for (TmdbGenre genre : tmdbFilmResponse.getGenres()) {
                genres.add(new Genre(genre.getId(), genre.getName()));
            }
        }

        return new Film(tmdbFilmResponse.getId(), tmdbFilmResponse.getTitle(), tmdbFilmResponse.getOriginalTitle(), parseDate(tmdbFilmResponse.getReleaseDate()), tmdbFilmResponse.getSynopsis(), tmdbFilmResponse.getPoster(), tmdbFilmResponse.getRating(), actors, director, genres);
    }

    private LocalDate parseDate(String date) {

        if (date == null || date.isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(date);

        } catch (Exception e) {
            return null;
        }
    }
}
