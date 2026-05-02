package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.dto.external.TmdbPopularResponse;
import com.zinemaapp.zinemaapp.dto.internal.FilmDTO;
import com.zinemaapp.zinemaapp.dto.external.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.infrastructure.TmdbClient;
import com.zinemaapp.zinemaapp.mapper.FilmMapper;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

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

    public List<FilmDTO> getPopularFilms() {
        try {
            TmdbPopularResponse tmdbPopularResponse = tmdbClient.getPopularFilms();

            List<FilmDTO> films = new ArrayList<>();

            for (TmdbFilmResponse tmdbFilm : tmdbPopularResponse.getResults()) {
                films.add(filmMapper.toFilmDTO(tmdbFilm));
            }

            return films;
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo las películas más populares", e);
        }
    }

    public FilmDTO getFilmById(int id) {
        TmdbFilmResponse tmdbFilmResponse = tmdbClient.getFilmById(id);
        return filmMapper.toFilmDTO(tmdbFilmResponse);
    }
}
