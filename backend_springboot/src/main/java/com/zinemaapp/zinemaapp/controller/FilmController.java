package com.zinemaapp.zinemaapp.controller;

import com.zinemaapp.zinemaapp.dto.internal.FilmDTO;
import com.zinemaapp.zinemaapp.application.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<FilmDTO>> getPopularFilms(@RequestParam int page) {
        return ResponseEntity.ok(filmService.getPopularFilms(page));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<FilmDTO>> getTopRatedFilms(@RequestParam int page) {
        return ResponseEntity.ok(filmService.getTopRatedFilms(page));

    }

    @GetMapping("/discover")
    public ResponseEntity<List<FilmDTO>> getFilmsbyGenre(@RequestParam int idGenre, @RequestParam int page){
        return ResponseEntity.ok(filmService.getFilmsByGenre(idGenre, page));
    }

    @GetMapping("/{id}")
    public FilmDTO getFilmById(@PathVariable int id) {
        return filmService.getFilmById(id);
    }
}
