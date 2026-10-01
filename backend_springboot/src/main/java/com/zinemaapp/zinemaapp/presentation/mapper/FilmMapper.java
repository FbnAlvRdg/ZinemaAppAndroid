package com.zinemaapp.zinemaapp.presentation.mapper;

import com.zinemaapp.zinemaapp.domain.model.Film;
import com.zinemaapp.zinemaapp.presentation.dto.credits.ActorDTO;
import com.zinemaapp.zinemaapp.presentation.dto.films.FilmDTO;
import com.zinemaapp.zinemaapp.presentation.dto.credits.GenreDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmMapper {
    private final ActorMapper actorMapper;
    private final GenreMapper genreMapper;

    public FilmMapper(ActorMapper actorMapper, GenreMapper genreMapper) {
        this.actorMapper = actorMapper;
        this.genreMapper = genreMapper;
    }

    public FilmDTO toDTO(Film film) {
        List<ActorDTO> actorsDTO = film.getActors()
                .stream()
                .map(actorMapper::toDTO)
                .toList();

        List<GenreDTO> genresDTO = film.getGenres()
                .stream()
                .map(genreMapper::toDTO)
                .toList();

        return new FilmDTO(
                film.getId(),
                film.getTitle(),
                film.getOriginalTitle(),
                film.getReleaseDate(),
                film.getSynopsis(),
                film.getPoster(),
                film.getRating(),
                actorsDTO,
                film.getDirector(),
                genresDTO
        );
    }
}
