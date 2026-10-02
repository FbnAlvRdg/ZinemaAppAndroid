package com.zinemaapp.zinemaapp.application.usecase.film;

import com.zinemaapp.zinemaapp.domain.model.film.Film;
import com.zinemaapp.zinemaapp.domain.repository.film.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFilmsByGenreUseCase {
    private final FilmRepository filmRepository;

    public GetFilmsByGenreUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getFilmsByGenre(int genreId, int page){
        return filmRepository.findByGenre(genreId, page);
    }
}
