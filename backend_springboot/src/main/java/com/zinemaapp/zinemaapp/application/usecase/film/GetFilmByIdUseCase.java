package com.zinemaapp.zinemaapp.application.usecase.film;

import com.zinemaapp.zinemaapp.domain.model.film.Film;
import com.zinemaapp.zinemaapp.domain.repository.film.FilmRepository;
import org.springframework.stereotype.Service;

@Service
public class GetFilmByIdUseCase {
    private final FilmRepository filmRepository;

    public GetFilmByIdUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film getFilmById(int id){
        return filmRepository.findById(id);
    }
}
