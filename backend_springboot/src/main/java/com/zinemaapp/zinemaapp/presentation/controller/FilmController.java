package com.zinemaapp.zinemaapp.presentation.controller;

import com.zinemaapp.zinemaapp.application.usecase.film.GetFilmByIdUseCase;
import com.zinemaapp.zinemaapp.application.usecase.film.GetFilmsByGenreUseCase;
import com.zinemaapp.zinemaapp.application.usecase.film.GetPopularFilmsUseCase;
import com.zinemaapp.zinemaapp.application.usecase.film.GetTopRatedFilmsUseCase;
import com.zinemaapp.zinemaapp.presentation.dto.films.FilmDTO;
import com.zinemaapp.zinemaapp.presentation.mapper.FilmMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class FilmController {
    private final GetPopularFilmsUseCase getPopularFilmsUseCase;
    private final GetTopRatedFilmsUseCase getTopRatedFilmsUseCase;
    private final GetFilmsByGenreUseCase getFilmsByGenreUseCase;
    private final GetFilmByIdUseCase getFilmByIdUseCase;
    private final FilmMapper filmMapper;

    public FilmController(GetPopularFilmsUseCase getPopularFilmsUseCase, GetTopRatedFilmsUseCase getTopRatedFilmsUseCase, GetFilmsByGenreUseCase getFilmsByGenreUseCase, GetFilmByIdUseCase getFilmById, FilmMapper filmMapper) {
        this.getPopularFilmsUseCase = getPopularFilmsUseCase;
        this.getTopRatedFilmsUseCase = getTopRatedFilmsUseCase;
        this.getFilmsByGenreUseCase = getFilmsByGenreUseCase;
        this.getFilmByIdUseCase = getFilmById;
        this.filmMapper = filmMapper;
    }


    @GetMapping("/popular")
    public ResponseEntity<List<FilmDTO>> getPopularFilms(@RequestParam int page) {
        List<FilmDTO> films = getPopularFilmsUseCase.getPopularFilms(page)
                .stream()
                .map(filmMapper::toDTO)
                .toList();

        return ResponseEntity.ok(films);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<FilmDTO>> getTopRatedFilms(@RequestParam int page) {
        List<FilmDTO> films = getTopRatedFilmsUseCase.getTopRatedFilms(page)
                .stream()
                .map(filmMapper::toDTO)
                .toList();

        return ResponseEntity.ok(films);

    }

    @GetMapping("/explore")
    public ResponseEntity<List<FilmDTO>> getFilmsbyGenre(
            @RequestParam(name = "genre_id") int idGenre,
            @RequestParam int page
    ) {
        List<FilmDTO> films = getFilmsByGenreUseCase.getFilmsByGenre(idGenre, page)
                .stream()
                .map(filmMapper::toDTO)
                .toList();

        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable int id) {
        FilmDTO film = filmMapper.toDTO(getFilmByIdUseCase.getFilmById(id));
        return ResponseEntity.ok(film);
    }
}
