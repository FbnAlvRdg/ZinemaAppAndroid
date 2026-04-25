package com.zinemaapp.zinemaapp.controller;

import com.zinemaapp.zinemaapp.dto.FilmDTO;
import com.zinemaapp.zinemaapp.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping("/popular")
    public List<FilmDTO> getPopularFilms(){
        return filmService.getPopularFilms();
    }
}
