package com.zinemaapp.zinemaapp.domain.repository.tvseries;

import com.zinemaapp.zinemaapp.domain.model.tvserie.TvSerie;

import java.util.List;

public interface TvSerieRepository {
    List<TvSerie> findMostPopular(int page);

    List<TvSerie> findTopRated(int page);

    List<TvSerie> findByGenre(int idGenre, int page);

    TvSerie findById(int id);
}
