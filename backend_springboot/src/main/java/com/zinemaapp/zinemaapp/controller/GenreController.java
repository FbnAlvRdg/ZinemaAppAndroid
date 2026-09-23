package com.zinemaapp.zinemaapp.controller;

import com.zinemaapp.zinemaapp.application.GenreService;
import com.zinemaapp.zinemaapp.dto.internal.GenreDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @RequestMapping("/films")
    public ResponseEntity<List<GenreDTO>> getFilmGenres() {
        return ResponseEntity.ok(genreService.getFilmGenres());
    }

    @GetMapping
    @RequestMapping("/tv")
    public ResponseEntity<List<GenreDTO>> getTvGenres(){
        return ResponseEntity.ok(genreService.getTvGenres());
    }
}
