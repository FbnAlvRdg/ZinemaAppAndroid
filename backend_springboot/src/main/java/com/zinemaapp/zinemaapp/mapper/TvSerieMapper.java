package com.zinemaapp.zinemaapp.mapper;

import com.zinemaapp.zinemaapp.dto.external.TmdbCast;
import com.zinemaapp.zinemaapp.dto.external.TmdbGenre;
import com.zinemaapp.zinemaapp.dto.external.tvserie.TmdbTvSerieResponse;
import com.zinemaapp.zinemaapp.dto.internal.ActorDTO;
import com.zinemaapp.zinemaapp.dto.internal.GenreDTO;
import com.zinemaapp.zinemaapp.dto.internal.TvSerieDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TvSerieMapper {
    public TvSerieDTO toTvSerieDTO(TmdbTvSerieResponse tmdbTvSerieResponse) {

        List<ActorDTO> actors = new ArrayList<>();
        int actorCounter = 0;
        if (tmdbTvSerieResponse.getCredits() != null && tmdbTvSerieResponse.getCredits().getCast() != null) {
            for (TmdbCast cast : tmdbTvSerieResponse.getCredits().getCast()) {
                if (actorCounter == 5) {
                    break;
                } else {
                    actors.add(new ActorDTO(
                            cast.getId(),
                            cast.getName(),
                            cast.getCharacter()
                    ));
                }
                actorCounter++;
            }
        }

        List<GenreDTO> genres = new ArrayList<>();
        if (tmdbTvSerieResponse.getGenres() != null) {
            for (TmdbGenre genre : tmdbTvSerieResponse.getGenres()) {
                genres.add(new GenreDTO(
                        genre.getId(),
                        genre.getName()
                ));
            }
        }

        return new TvSerieDTO(
                tmdbTvSerieResponse.getId(),
                tmdbTvSerieResponse.getName(),
                tmdbTvSerieResponse.getOriginCountry(),
                tmdbTvSerieResponse.getOverview(),
                "https://image.tmdb.org/t/p/w500" + tmdbTvSerieResponse.getPoster(),
                tmdbTvSerieResponse.getRating(),
                parseDate(tmdbTvSerieResponse.getFirstAireDate()),
                genres,
                actors
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
