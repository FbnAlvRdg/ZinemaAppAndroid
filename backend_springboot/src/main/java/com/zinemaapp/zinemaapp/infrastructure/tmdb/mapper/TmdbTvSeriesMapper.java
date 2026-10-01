package com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper;

import com.zinemaapp.zinemaapp.domain.model.TvSerie;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.tvserie.TmdbTvSeriesResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmdbTvSeriesMapper {
    private final TmdbTvSerieMapper tmdbTvSerieMapper;

    public TmdbTvSeriesMapper(TmdbTvSerieMapper tmdbTvSerieMapper) {
        this.tmdbTvSerieMapper = tmdbTvSerieMapper;
    }

    public List<TvSerie> toDomain(TmdbTvSeriesResponse tmdbTvSeriesResponse) {
        return tmdbTvSeriesResponse.getResults().stream().map(tmdbTvSerieMapper::toDomain).toList();

    }
}
