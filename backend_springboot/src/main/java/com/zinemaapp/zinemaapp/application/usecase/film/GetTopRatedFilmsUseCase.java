package com.zinemaapp.zinemaapp.application.usecase.film;

import com.zinemaapp.zinemaapp.domain.model.Film;
import com.zinemaapp.zinemaapp.domain.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTopRatedFilmsUseCase {
    private final FilmRepository filmRepository;

    public GetTopRatedFilmsUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getTopRatedFilms(int page) {
        return filmRepository.findTopRated(page);
    }
}
