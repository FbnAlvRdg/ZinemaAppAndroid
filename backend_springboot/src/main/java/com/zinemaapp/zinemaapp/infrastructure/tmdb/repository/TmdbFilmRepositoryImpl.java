package com.zinemaapp.zinemaapp.infrastructure.tmdb.repository;

import com.zinemaapp.zinemaapp.domain.model.Film;
import com.zinemaapp.zinemaapp.domain.repository.FilmRepository;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.client.TmdbClient;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbFilmMapper;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbFilmsMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmdbFilmRepositoryImpl implements FilmRepository {
    private final TmdbClient tmdbClient;
    private final TmdbFilmsMapper tmdbFilmsMapper;
    private final TmdbFilmMapper tmdbFilmMapper;

    public TmdbFilmRepositoryImpl(TmdbClient tmdbClient, TmdbFilmsMapper tmdbFilmsMapper, TmdbFilmMapper tmdbFilmMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbFilmsMapper = tmdbFilmsMapper;
        this.tmdbFilmMapper = tmdbFilmMapper;
    }


    @Override
    public List<Film> findMostPopular(int page) {
        return tmdbFilmsMapper.toDomain(tmdbClient.getMostPopularFilms(page));
    }

    @Override
    public List<Film> findTopRated(int page) {
        return tmdbFilmsMapper.toDomain(tmdbClient.getTopRatedFilms(page));
    }

    @Override
    public List<Film> findByGenre(int idGenre, int page) {
        return tmdbFilmsMapper.toDomain(tmdbClient.getFilmsByGenre(idGenre, page));
    }

    @Override
    public Film findById(int id) {
        return tmdbFilmMapper.toDomain(tmdbClient.getFilmById(id));
    }
}
