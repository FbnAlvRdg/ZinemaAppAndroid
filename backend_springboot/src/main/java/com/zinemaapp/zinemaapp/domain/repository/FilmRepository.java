package com.zinemaapp.zinemaapp.domain.repository;

import com.zinemaapp.zinemaapp.domain.model.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> findMostPopular(int page);

    List<Film> findTopRated(int page);

    List<Film> findByGenre(int idGenre, int page);

    Film findById(int id);
}
