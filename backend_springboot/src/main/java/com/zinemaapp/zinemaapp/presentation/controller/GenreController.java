package com.zinemaapp.zinemaapp.presentation.controller;

import com.zinemaapp.zinemaapp.application.usecase.genre.GetFilmsGenresUseCase;
import com.zinemaapp.zinemaapp.application.usecase.genre.GetTvSeriesGenresUseCase;
import com.zinemaapp.zinemaapp.presentation.dto.credits.GenreDTO;
import com.zinemaapp.zinemaapp.presentation.mapper.GenreMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GetFilmsGenresUseCase getFilmsGenresUseCase;
    private final GetTvSeriesGenresUseCase getTvSeriesGenresUseCase;
    private final GenreMapper genreMapper;

    public GenreController(GetFilmsGenresUseCase getFilmsGenresUseCase, GetTvSeriesGenresUseCase getTvSeriesGenresUseCase, GenreMapper genreMapper) {
        this.getFilmsGenresUseCase = getFilmsGenresUseCase;
        this.getTvSeriesGenresUseCase = getTvSeriesGenresUseCase;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    @RequestMapping("/films")
    public ResponseEntity<List<GenreDTO>> getFilmGenres() {
        List<GenreDTO> filmGenres = getFilmsGenresUseCase.getFilmGenres().stream().map(genreMapper::toDTO).toList();
        return ResponseEntity.ok(filmGenres);
    }

    @GetMapping
    @RequestMapping("/tv")
    public ResponseEntity<List<GenreDTO>> getTvGenres() {
        List<GenreDTO> tvSeriesGenres = getTvSeriesGenresUseCase.getTvSeriesGenres().stream().map(genreMapper::toDTO).toList();
        return ResponseEntity.ok(tvSeriesGenres);
    }

}
