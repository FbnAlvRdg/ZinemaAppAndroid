package com.zinemaapp.zinemaapp.domain.repository.genre;

import com.zinemaapp.zinemaapp.domain.model.common.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> findFilmsGenres();
    List<Genre> findTvSerieGenres();
}
