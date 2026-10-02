package com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper;

import com.zinemaapp.zinemaapp.domain.model.common.Actor;
import com.zinemaapp.zinemaapp.domain.model.common.Genre;
import com.zinemaapp.zinemaapp.domain.model.tvserie.TvSerie;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.credits.TmdbCast;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.genre.TmdbGenre;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.tvserie.TmdbTvSerieResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TmdbTvSerieMapper {
    public TvSerie toDomain(TmdbTvSerieResponse tmdbTvSerieResponse) {

        List<Actor> actors = new ArrayList<>();
        int actorCounter = 0;
        if (tmdbTvSerieResponse.getCredits() != null && tmdbTvSerieResponse.getCredits().getCast() != null) {
            for (TmdbCast cast : tmdbTvSerieResponse.getCredits().getCast()) {
                if (actorCounter == 5) {
                    break;
                } else {
                    actors.add(new Actor(
                            cast.getId(),
                            cast.getName(),
                            cast.getCharacter()
                    ));
                }
                actorCounter++;
            }
        }

        List<Genre> genres = new ArrayList<>();
        if (tmdbTvSerieResponse.getGenres() != null) {
            for (TmdbGenre genre : tmdbTvSerieResponse.getGenres()) {
                genres.add(new Genre(
                        genre.getId(),
                        genre.getName()
                ));
            }
        }

        return new TvSerie(
                tmdbTvSerieResponse.getId(),
                tmdbTvSerieResponse.getName(),
                tmdbTvSerieResponse.getOriginCountry(),
                tmdbTvSerieResponse.getOverview(),
                "https://image.tmdb.org/t/p/w500" + tmdbTvSerieResponse.getPoster(),
                actors,
                genres,
                parseDate(tmdbTvSerieResponse.getFirstAireDate()),
                tmdbTvSerieResponse.getRating()
        );
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
