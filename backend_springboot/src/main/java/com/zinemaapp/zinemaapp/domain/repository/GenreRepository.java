package com.zinemaapp.zinemaapp.domain.repository;

import com.zinemaapp.zinemaapp.domain.model.Film;
import com.zinemaapp.zinemaapp.domain.model.Genre;
import com.zinemaapp.zinemaapp.domain.model.TvSerie;

import java.util.List;

public interface GenreRepository {
    List<Genre> findFilmsGenres();
    List<Genre> findTvSerieGenres();
}
