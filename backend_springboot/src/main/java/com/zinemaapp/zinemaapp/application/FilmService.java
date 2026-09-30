package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.film.TmdbFilmsResponse;
import com.zinemaapp.zinemaapp.dto.internal.FilmDTO;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.film.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.client.TmdbClient;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbFilmMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private final TmdbClient tmdbClient;
    private final TmdbFilmMapper tmdbFilmMapper;

    public FilmService(TmdbClient tmdbClient, TmdbFilmMapper tmdbFilmMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbFilmMapper = tmdbFilmMapper;
    }

    public List<FilmDTO> getPopularFilms(int page) {
        try {
            TmdbFilmsResponse tmdbPopularResponse = tmdbClient.getMostPopularFilms(page);

            List<FilmDTO> filmDTOS = new ArrayList<>();

            for (TmdbFilmResponse tmdbFilm : tmdbPopularResponse.getResults()) {
                filmDTOS.add(tmdbFilmMapper.toDomain(tmdbFilm));
            }

            return filmDTOS;
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo las películas más populares", e);
        }
    }

    public List<FilmDTO> getTopRatedFilms(int page) {
        TmdbFilmsResponse tmdbTopRatedResponse = tmdbClient.getTopRatedFilms(page);

        List<FilmDTO> filmDTOS = new ArrayList<>();

        for (TmdbFilmResponse tmdbFilm : tmdbTopRatedResponse.getResults()) {
            filmDTOS.add(tmdbFilmMapper.toDomain(tmdbFilm));
        }

        return filmDTOS;
    }

    public List<FilmDTO> getFilmsByGenre(int idGenre, int page) {
        TmdbFilmsResponse tmdbFilmsResponse = tmdbClient.getFilmsByGenre(idGenre, page);

        List<FilmDTO> filmDTOS = new ArrayList<>();

        for (TmdbFilmResponse tmdbFilm : tmdbFilmsResponse.getResults()) {
            filmDTOS.add(tmdbFilmMapper.toDomain(tmdbFilm));
        }

        return filmDTOS;
    }

    public FilmDTO getFilmById(int id) {
        TmdbFilmResponse tmdbFilmResponse = tmdbClient.getFilmById(id);
        return tmdbFilmMapper.toDomain(tmdbFilmResponse);
    }
}
