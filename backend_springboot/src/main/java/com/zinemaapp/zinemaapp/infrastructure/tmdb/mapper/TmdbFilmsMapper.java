package com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper;

import com.zinemaapp.zinemaapp.domain.model.Film;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.film.TmdbFilmsResponse;

import java.util.List;

public class TmdbFilmsMapper {
    private final TmdbFilmMapper tmdbFilmMapper;

    public TmdbFilmsMapper(TmdbFilmMapper tmdbFilmMapper) {
        this.tmdbFilmMapper = tmdbFilmMapper;
    }

    public List<Film> toDomain(TmdbFilmsResponse tmdbFilmsResponse){
        return tmdbFilmsResponse.getResults().stream().map(tmdbFilmMapper::toDomain).toList();
    }
}
