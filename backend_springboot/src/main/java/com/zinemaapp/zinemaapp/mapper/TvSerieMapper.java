package com.zinemaapp.zinemaapp.mapper;

import com.zinemaapp.zinemaapp.dto.external.tvserie.TmdbTvSerieResponse;
import com.zinemaapp.zinemaapp.dto.internal.TvSerieDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TvSerieMapper {
    public TvSerieDTO toTvSerieDTO(TmdbTvSerieResponse tmdbTvSerieResponse) {
        return new TvSerieDTO(
                tmdbTvSerieResponse.getId(),
                tmdbTvSerieResponse.getName(),
                tmdbTvSerieResponse.getOriginCountry(),
                tmdbTvSerieResponse.getOverview(),
                tmdbTvSerieResponse.getPoster(),
                tmdbTvSerieResponse.getRating(),
                parseDate(tmdbTvSerieResponse.getFirstAireDate())
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
