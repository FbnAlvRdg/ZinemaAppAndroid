package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.dto.external.TmdbGenre;
import com.zinemaapp.zinemaapp.dto.external.TmdbGenreResponse;
import com.zinemaapp.zinemaapp.dto.internal.GenreDTO;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.client.TmdbClient;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbGenreMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    private final TmdbClient tmdbClient;
    private final TmdbGenreMapper tmdbGenreMapper;

    public GenreService(TmdbClient tmdbClient, TmdbGenreMapper tmdbGenreMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbGenreMapper = tmdbGenreMapper;
    }

    public List<GenreDTO> getFilmGenres() {

        TmdbGenreResponse tmdbGenreResponse = tmdbClient.getFilmGenres();
        List<GenreDTO> genres = new ArrayList<>();

        for (TmdbGenre tmdbGenre : tmdbGenreResponse.getGenres()) {
            genres.add(tmdbGenreMapper.toGenreDTO(tmdbGenre));
        }

        return genres;
    }

    public List<GenreDTO> getTvGenres() {
        TmdbGenreResponse tmdbGenreResponse = tmdbClient.getTvGenres();

        return tmdbGenreResponse.getGenres()
                .stream()
                .map(tmdbGenreMapper::toGenreDTO)
                .toList();
    }
}
