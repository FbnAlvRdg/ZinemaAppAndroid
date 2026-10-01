package com.zinemaapp.zinemaapp.presentation.controller;

import com.zinemaapp.zinemaapp.application.usecase.tvserie.GetMostPopularTvSerieUseCase;
import com.zinemaapp.zinemaapp.application.usecase.tvserie.GetTopRatedTvSerieUseCase;
import com.zinemaapp.zinemaapp.application.usecase.tvserie.GetTvSerieByIdUseCase;
import com.zinemaapp.zinemaapp.application.usecase.tvserie.GetTvSeriesByGenreUseCase;
import com.zinemaapp.zinemaapp.presentation.dto.tvseries.TvSerieDTO;
import com.zinemaapp.zinemaapp.presentation.mapper.TvSerieMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tv")
public class TvSerieController {
    private final GetMostPopularTvSerieUseCase getMostPopularTvSerieUseCase;
    private final GetTopRatedTvSerieUseCase getTopRatedTvSerieUseCase;
    private final GetTvSeriesByGenreUseCase getTvSeriesByGenreUseCase;
    private final GetTvSerieByIdUseCase getTvSerieByIdUseCase;
    private final TvSerieMapper tvSerieMapper;

    public TvSerieController(GetMostPopularTvSerieUseCase getMostPopularTvSerieUseCase, GetTopRatedTvSerieUseCase getTopRatedTvSerieUseCase, GetTvSeriesByGenreUseCase getTvSeriesByGenreUseCase, GetTvSerieByIdUseCase getTvSerieByIdUseCase, TvSerieMapper tvSerieMapper) {
        this.getMostPopularTvSerieUseCase = getMostPopularTvSerieUseCase;
        this.getTopRatedTvSerieUseCase = getTopRatedTvSerieUseCase;
        this.getTvSeriesByGenreUseCase = getTvSeriesByGenreUseCase;
        this.getTvSerieByIdUseCase = getTvSerieByIdUseCase;
        this.tvSerieMapper = tvSerieMapper;
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<TvSerieDTO>> getTopRatedSeries(@RequestParam int page) {
        List<TvSerieDTO> series = getTopRatedTvSerieUseCase.getTopRatedSeries(page)
                .stream()
                .map(tvSerieMapper::toDTO)
                .toList();

        return ResponseEntity.ok(series);
    }

    @GetMapping("/most-popular")
    public ResponseEntity<List<TvSerieDTO>> getMostPopularSeries(@RequestParam int page) {
        List<TvSerieDTO> series = getMostPopularTvSerieUseCase.getMostPopularSeries(page)
                .stream()
                .map(tvSerieMapper::toDTO)
                .toList();

        return ResponseEntity.ok(series);
    }

    @GetMapping("/explore")
    public ResponseEntity<List<TvSerieDTO>> getTvSeriesByGenre(
            @RequestParam int idGenre,
            @RequestParam int page
    ) {
        List<TvSerieDTO> series = getTvSeriesByGenreUseCase.getSeriesByGenre(idGenre, page)
                .stream()
                .map(tvSerieMapper::toDTO)
                .toList();

        return ResponseEntity.ok(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvSerieDTO> getSerieById(@PathVariable int id) {
        return ResponseEntity.ok(tvSerieMapper.toDTO(getTvSerieByIdUseCase.getTvSerieById(id)));
    }
}
