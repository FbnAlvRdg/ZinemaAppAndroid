package com.zinemaapp.zinemaapp.presentation.mapper;

import com.zinemaapp.zinemaapp.domain.model.TvSerie;
import com.zinemaapp.zinemaapp.presentation.dto.credits.ActorDTO;
import com.zinemaapp.zinemaapp.presentation.dto.credits.GenreDTO;
import com.zinemaapp.zinemaapp.presentation.dto.tvseries.TvSerieDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TvSerieMapper {
    private final GenreMapper genreMapper;
    private final ActorMapper actorMapper;

    public TvSerieMapper(GenreMapper genreMapper, ActorMapper actorMapper) {
        this.genreMapper = genreMapper;
        this.actorMapper = actorMapper;
    }

    public TvSerieDTO toDTO(TvSerie tvSerie) {
        List<ActorDTO> actorsDTO = tvSerie.getActors()
                .stream()
                .map(actorMapper::toDTO)
                .toList();

        List<GenreDTO> genresDTO = tvSerie.getGenres()
                .stream()
                .map(genreMapper::toDTO)
                .toList();

        return new TvSerieDTO(
                tvSerie.getId(),
                tvSerie.getName(),
                tvSerie.getOriginCountry(),
                tvSerie.getOverview(),
                tvSerie.getPoster(),
                tvSerie.getRating(),
                tvSerie.getFirstAireDate(),
                genresDTO,
                actorsDTO
        );
    }
}
