package com.zinemaapp.zinemaapp.application.usecase.genre;

import com.zinemaapp.zinemaapp.domain.model.common.Genre;
import com.zinemaapp.zinemaapp.domain.repository.genre.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFilmsGenresUseCase {
    private final GenreRepository genreRepository;

    public GetFilmsGenresUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getFilmGenres() {
        return genreRepository.findFilmsGenres();
    }
}
