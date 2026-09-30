package com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper;

import com.zinemaapp.zinemaapp.domain.model.Genre;
import com.zinemaapp.zinemaapp.dto.external.TmdbGenre;
import org.springframework.stereotype.Component;

@Component
public class TmdbGenreMapper {

    public Genre toDomain(TmdbGenre tmdbGenre) {
        return new Genre(
                tmdbGenre.getId(),
                tmdbGenre.getName()
        );
    }
}
