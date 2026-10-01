package com.zinemaapp.zinemaapp.application.usecase.film;

import com.zinemaapp.zinemaapp.domain.model.Film;
import com.zinemaapp.zinemaapp.domain.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPopularFilmsUseCase {
    private final FilmRepository filmRepository;

    public GetPopularFilmsUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getPopularFilms(int page) {
        return filmRepository.findMostPopular(page);
    }
}
