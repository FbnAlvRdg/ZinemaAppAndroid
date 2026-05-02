package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.dto.FilmDTO;
import com.zinemaapp.zinemaapp.infrastructure.TmdbClient;
import com.zinemaapp.zinemaapp.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private final TmdbClient tmdbClient;
    private final FilmMapper filmMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FilmService(TmdbClient tmdbClient, FilmMapper filmMapper) {
        this.tmdbClient = tmdbClient;
        this.filmMapper = filmMapper;
    }

    public List<FilmDTO> getPopularFilms() {
        try {
            String json = tmdbClient.getPopularFilms();

            JsonNode root = objectMapper.readTree(json);
            JsonNode results = root.path("results");

            List<FilmDTO> films = new ArrayList<>();

            for (JsonNode jsonNode : results) {
                films.add(filmMapper.fromJson(jsonNode));
            }

            return films;
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo las películas más populares", e);
        }
    }

    public FilmDTO getFilmById(int id) {
        FilmDTO filmDTO = tmdbClient.getFilmById(id);
        return filmDTO;
    }

}
