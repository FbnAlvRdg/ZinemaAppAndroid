package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.dto.external.TmdbGenre;
import com.zinemaapp.zinemaapp.dto.external.TmdbGenreResponse;
import com.zinemaapp.zinemaapp.dto.internal.GenreDTO;
import com.zinemaapp.zinemaapp.infrastructure.TmdbClient;
import com.zinemaapp.zinemaapp.mapper.GenreMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    private final TmdbClient tmdbClient;
    private final GenreMapper genreMapper;

    public GenreService(TmdbClient tmdbClient, GenreMapper genreMapper) {
        this.tmdbClient = tmdbClient;
        this.genreMapper = genreMapper;
    }

    public List<GenreDTO> getFilmGenres() {

        TmdbGenreResponse tmdbGenreResponse = tmdbClient.getFilmGenres();
        List<GenreDTO> genres = new ArrayList<>();

        for (TmdbGenre tmdbGenre : tmdbGenreResponse.getGenres()) {
            genres.add(genreMapper.toGenreDTO(tmdbGenre));
        }

        return genres;
    }

    public List<GenreDTO> getTvGenres() {
        TmdbGenreResponse tmdbGenreResponse = tmdbClient.getTvGenres();

        return tmdbGenreResponse.getGenres()
                .stream()
                .map(genreMapper::toGenreDTO)
                .toList();
    }
}
