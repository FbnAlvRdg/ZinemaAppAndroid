package com.zinemaapp.zinemaapp.domain.repository;

import com.zinemaapp.zinemaapp.domain.model.TvSerie;

import java.util.List;

public interface TvSerieRepository {
    List<TvSerie> findMostPopular(int page);

    List<TvSerie> findTopRated(int page);

    List<TvSerie> findByGenre(int idGenre, int page);

    TvSerie findById(int id);
}
