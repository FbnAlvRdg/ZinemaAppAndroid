package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.dto.external.film.TmdbFilmsResponse;
import com.zinemaapp.zinemaapp.dto.internal.FilmDTO;
import com.zinemaapp.zinemaapp.dto.external.film.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.infrastructure.TmdbClient;
import com.zinemaapp.zinemaapp.mapper.FilmMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private final TmdbClient tmdbClient;
    private final FilmMapper filmMapper;

    public FilmService(TmdbClient tmdbClient, FilmMapper filmMapper) {
        this.tmdbClient = tmdbClient;
        this.filmMapper = filmMapper;
    }

    public List<FilmDTO> getPopularFilms(int page) {
        try {
            TmdbFilmsResponse tmdbPopularResponse = tmdbClient.getPopularFilms(page);

            List<FilmDTO> films = new ArrayList<>();

            for (TmdbFilmResponse tmdbFilm : tmdbPopularResponse.getResults()) {
                films.add(filmMapper.toFilmDTO(tmdbFilm));
            }

            return films;
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo las películas más populares", e);
        }
    }

    public List<FilmDTO> getTopRatedFilms(int page) {
        TmdbFilmsResponse tmdbTopRatedResponse = tmdbClient.getTopRatedFilms(page);

        List<FilmDTO> films = new ArrayList<>();

        for (TmdbFilmResponse tmdbFilm : tmdbTopRatedResponse.getResults()) {
            films.add(filmMapper.toFilmDTO(tmdbFilm));
        }

        return films;
    }

    public List<FilmDTO> getFilmsByGenre(int idGenre) {
        TmdbFilmsResponse tmdbFilmsResponse = tmdbClient.getFilmsByGenre(idGenre);

        List<FilmDTO> films = new ArrayList<>();

        for (TmdbFilmResponse tmdbFilm : tmdbFilmsResponse.getResults()) {
            films.add(filmMapper.toFilmDTO(tmdbFilm));
        }

        return films;
    }

    public FilmDTO getFilmById(int id) {
        TmdbFilmResponse tmdbFilmResponse = tmdbClient.getFilmById(id);
        return filmMapper.toFilmDTO(tmdbFilmResponse);
    }
}
