package com.zinemaapp.zinemaapp.mapper;

import com.zinemaapp.zinemaapp.dto.external.TmdbCast;
import com.zinemaapp.zinemaapp.dto.external.TmdbCrew;
import com.zinemaapp.zinemaapp.dto.external.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.dto.external.TmdbGenre;
import com.zinemaapp.zinemaapp.dto.internal.ActorDTO;
import com.zinemaapp.zinemaapp.dto.internal.FilmDTO;
import com.zinemaapp.zinemaapp.dto.internal.GenreDTO;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmMapper {

    public FilmDTO fromJson(JsonNode jsonNode) {
        FilmDTO filmDTO = new FilmDTO(
                jsonNode.path("id").asInt(0),
                jsonNode.path("title").asString(""),
                jsonNode.path("original_title").asString(""),
                parseDate(jsonNode.path("release_date").asString(null)),
                jsonNode.path("overview").asString(""),
                jsonNode.path("poster_path").asString(""),
                jsonNode.path("vote_average").asDouble(0.0)
        );
        return filmDTO;
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

    public FilmDTO toFilmDTO(TmdbFilmResponse tmdbFilmResponse) {

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

        List<ActorDTO> actors = new ArrayList<>();
        if (tmdbFilmResponse.getCredits() != null && tmdbFilmResponse.getCredits().getCast() != null) {
            for (TmdbCast cast : tmdbFilmResponse.getCredits().getCast()) {
                if (counter >= 5) {
                    break;
                } else {
                    actors.add(new ActorDTO(
                            cast.getId(),
                            cast.getName(),
                            cast.getCharacter()
                    ));
                    counter++;
                }
            }
        }

        List<GenreDTO> genres = new ArrayList<>();

        if (tmdbFilmResponse.getGenres() != null) {
            for (TmdbGenre genre : tmdbFilmResponse.getGenres()) {
                genres.add(new GenreDTO(
                        genre.getId(),
                        genre.getName()
                ));
            }
        }

        return new FilmDTO(
                tmdbFilmResponse.getId(),
                tmdbFilmResponse.getTitle(),
                tmdbFilmResponse.getOriginalTitle(),
                parseDate(tmdbFilmResponse.getReleaseDate()),
                tmdbFilmResponse.getSynopsis(),
                tmdbFilmResponse.getPoster(),
                tmdbFilmResponse.getRating(),
                actors,
                director,
                genres
        );
    }
}
