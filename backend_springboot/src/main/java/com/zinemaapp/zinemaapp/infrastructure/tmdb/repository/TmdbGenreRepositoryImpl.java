package com.zinemaapp.zinemaapp.infrastructure.tmdb.repository;

import com.zinemaapp.zinemaapp.domain.model.common.Genre;
import com.zinemaapp.zinemaapp.domain.repository.genre.GenreRepository;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.client.TmdbClient;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbGenreMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmdbGenreRepositoryImpl implements GenreRepository {
    private final TmdbClient tmdbClient;
    private final TmdbGenreMapper tmdbGenreMapper;

    public TmdbGenreRepositoryImpl(TmdbClient tmdbClient, TmdbGenreMapper tmdbGenreMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbGenreMapper = tmdbGenreMapper;
    }


    @Override
    public List<Genre> findFilmsGenres() {
        return tmdbClient.getFilmGenres().getGenres().stream().map(tmdbGenreMapper::toDomain).toList();
    }

    @Override
    public List<Genre> findTvSerieGenres() {
        return tmdbClient.getTvGenres().getGenres().stream().map(tmdbGenreMapper::toDomain).toList();
    }
}
