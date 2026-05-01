package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.dto.FilmDTO;
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
    @Value("${tmdb.api.key}")
    private String API_KEY;

    public List<FilmDTO> getPopularFilms() {
        try {
            String url = "https://api.themoviedb.org/3/movie/popular?api_key="
                    + API_KEY + "&language=es-ES";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode root = objectMapper.readTree(response.body());
            JsonNode results = root.get("results");

            List<FilmDTO> films = new ArrayList<>();

            for (JsonNode jsonNode : results){
                FilmDTO filmDTO = objectMapper.treeToValue(jsonNode, FilmDTO.class);
                films.add(filmDTO);
            }

            return films;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
