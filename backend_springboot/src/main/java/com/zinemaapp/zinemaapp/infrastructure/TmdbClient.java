package com.zinemaapp.zinemaapp.infrastructure;

import com.zinemaapp.zinemaapp.dto.external.film.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.dto.external.film.TmdbFilmsResponse;
import com.zinemaapp.zinemaapp.dto.external.tvserie.TmdbTvSerieResponse;
import com.zinemaapp.zinemaapp.dto.external.tvserie.TmdbTvSeriesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TmdbClient {
    @Value("${tmdb.api.key}")
    private String API_KEY;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public TmdbClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public TmdbFilmsResponse getPopularFilms(int page) {
        try {
            String url = buildUrl("/movie/popular") + "&page=" + page;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw new RuntimeException("Error (Respuesta Inválida): " + response.statusCode() + "body: " + response.body());
            }

            return objectMapper.readValue(response.body(), TmdbFilmsResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Error en la conexión: ", e);
        }
    }

    public TmdbFilmsResponse getTopRatedFilms(int page) {
        String url = buildUrl("/movie/top_rated") + "&page=" + page;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error de conexión con TMDB", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Petición interrumpida", e);
        }

        if (response.statusCode() >= 400) {
            throw new RuntimeException("Error: " + response.statusCode() + "body: " + response.body());
        }

        return objectMapper.readValue(response.body(), TmdbFilmsResponse.class);

    }

    public TmdbFilmsResponse getFilmsByGenre(int idGenre) {
        String url = buildUrl("/discover/movie/") + "?with_genres=" + idGenre;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error de conexión con TMDB: ", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Petición interrumpida: ", e);
        }

        if (response.statusCode() >= 400) {
            throw new RuntimeException("Error: " + response.statusCode() + " body: " + response.body());
        }

        return objectMapper.readValue(response.body(), TmdbFilmsResponse.class);
    }

    public TmdbFilmResponse getFilmById(int id) {
        try {
            String url = buildUrl("/movie/" + id) + "&append_to_response=credits";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw new RuntimeException("Error: " + response.statusCode() + "body: " + response.body());
            }

            return objectMapper.readValue(response.body(), TmdbFilmResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo la película con el id: ", e);
        }
    }

    public TmdbTvSerieResponse getSeriesById(int id) {
        try {
            String url = buildUrl("/tv/" + id) + "&append_to_response=credits";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw new RuntimeException("Error: " + response.statusCode() + "body: " + response.body());
            }

            return objectMapper.readValue(response.body(), TmdbTvSerieResponse.class);

        } catch (IOException e) {
            throw new RuntimeException("Error en la conexión con TMDB: ", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Petición interrumpida: ", e);
        }
    }

    public TmdbTvSeriesResponse getTopRatedSeries(int page) {
        String url = buildUrl("/tv/top_rated") + "&page=" + page;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error en la conexión con TMDB: ", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Petición interrumpida: ", e);
        }

        if (response.statusCode() >= 400) {
            throw new RuntimeException("Error: " + response.statusCode() + " body: " + response.body());
        }

        return objectMapper.readValue(response.body(), TmdbTvSeriesResponse.class);
    }

    public TmdbTvSeriesResponse getSeriesByGenre(int idGenre) {
        String url = buildUrl("/discover/tv") + "?with_genres=" + idGenre;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error en la conexión con TMDB ", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Petición interrumpida: ", e);
        }

        if (response.statusCode() >= 400){
            throw new RuntimeException("Error: " + response.statusCode() + " body: " + response.body());
        }

        return objectMapper.readValue(response.body(), TmdbTvSeriesResponse.class);
    }

    private String buildUrl(String endpoint) {
        return "https://api.themoviedb.org/3" + endpoint + "?api_key="
                + API_KEY + "&language=es-ES";
    }
}
