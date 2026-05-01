package com.zinemaapp.zinemaapp.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TmdbClient {
    @Value("${tmdb.api.key}")
    private String API_KEY;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String url = "https://api.themoviedb.org/3/movie/popular?api_key="
            + API_KEY + "&language=es-ES";

    public String getPopularFilms() {
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

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Error en la conexión: ", e);
        }
    }

    private String buildUrl(String endpoint) {
        return "https://api.themoviedb.org/3" + endpoint + "?api_key="
                + API_KEY + "&language=es-ES";
    }
}
