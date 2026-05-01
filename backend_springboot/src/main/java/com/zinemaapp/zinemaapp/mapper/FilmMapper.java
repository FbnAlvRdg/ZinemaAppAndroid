package com.zinemaapp.zinemaapp.mapper;

import com.zinemaapp.zinemaapp.dto.FilmDTO;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.time.LocalDate;

@Component
public class FilmMapper {

    public FilmDTO fromJson(JsonNode jsonNode) {
        FilmDTO filmDTO = new FilmDTO(
                jsonNode.path("id").asInt(0),
                jsonNode.path("title").asString(""),
                jsonNode.path("original_title").asString(""),
                parseDate(jsonNode.path("release_date").asString(null)),
                jsonNode.path("overview").asString(""),
                jsonNode.path("poster_path").asString(""),
                jsonNode.path("vote_average").asDouble(0.0)
        );
        return filmDTO;
    }

    private LocalDate parseDate(String date) {

        if (date == null || date.isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(date);

        } catch (Exception e) {
            return null;
        }
    }
}
