package com.zinemaapp.zinemaapp.mapper;

import com.zinemaapp.zinemaapp.dto.external.TmdbGenre;
import com.zinemaapp.zinemaapp.dto.internal.GenreDTO;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreDTO toGenreDTO(TmdbGenre tmdbGenre) {
        return new GenreDTO(
                tmdbGenre.getId(),
                tmdbGenre.getName()
        );
    }
}
