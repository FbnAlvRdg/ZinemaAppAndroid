package com.zinemaapp.zinemaapp.infrastructure;

import com.zinemaapp.zinemaapp.dto.external.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.dto.external.TmdbPopularResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


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

    public TmdbPopularResponse getPopularFilms() {
        try {
            String url = buildUrl("/movie/popular");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw new RuntimeException("Error: " + response.statusCode() + "body: " + response.body());
            }

            return objectMapper.readValue(response.body(), TmdbPopularResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Error en la conexión: ", e);
        }
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

    private String buildUrl(String endpoint) {
        return "https://api.themoviedb.org/3" + endpoint + "?api_key="
                + API_KEY + "&language=es-ES";
    }
}
