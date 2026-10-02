package com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper;

import com.zinemaapp.zinemaapp.domain.model.film.Film;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.film.TmdbFilmsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmdbFilmsMapper {
    private final TmdbFilmMapper tmdbFilmMapper;

    public TmdbFilmsMapper(TmdbFilmMapper tmdbFilmMapper) {
        this.tmdbFilmMapper = tmdbFilmMapper;
    }

    public List<Film> toDomain(TmdbFilmsResponse tmdbFilmsResponse) {
        return tmdbFilmsResponse.getResults().stream().map(tmdbFilmMapper::toDomain).toList();
    }
}
