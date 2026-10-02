package com.zinemaapp.zinemaapp.domain.repository.film;

import com.zinemaapp.zinemaapp.domain.model.film.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> findMostPopular(int page);

    List<Film> findTopRated(int page);

    List<Film> findByGenre(int idGenre, int page);

    Film findById(int id);
}
