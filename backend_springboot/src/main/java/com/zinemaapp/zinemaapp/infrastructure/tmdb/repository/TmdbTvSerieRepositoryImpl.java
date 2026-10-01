package com.zinemaapp.zinemaapp.infrastructure.tmdb.repository;

import com.zinemaapp.zinemaapp.domain.model.TvSerie;
import com.zinemaapp.zinemaapp.domain.repository.TvSerieRepository;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.client.TmdbClient;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbTvSerieMapper;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbTvSeriesMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmdbTvSerieRepositoryImpl implements TvSerieRepository {
    private final TmdbClient tmdbClient;
    private final TmdbTvSerieMapper tmdbTvSerieMapper;
    private final TmdbTvSeriesMapper tmdbTvSeriesMapper;

    public TmdbTvSerieRepositoryImpl(TmdbClient tmdbClient, TmdbTvSerieMapper tmdbTvSerieMapper, TmdbTvSeriesMapper tmdbTvSeriesMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbTvSerieMapper = tmdbTvSerieMapper;
        this.tmdbTvSeriesMapper = tmdbTvSeriesMapper;
    }

    @Override
    public List<TvSerie> findMostPopular(int page) {
        return tmdbTvSeriesMapper.toDomain(tmdbClient.getMostPopularSeries(page));
    }

    @Override
    public List<TvSerie> findTopRated(int page) {
        return tmdbTvSeriesMapper.toDomain(tmdbClient.getTopRatedSeries(page));
    }

    @Override
    public List<TvSerie> findByGenre(int idGenre, int page) {
        return tmdbTvSeriesMapper.toDomain(tmdbClient.getSeriesByGenre(idGenre, page));
    }

    @Override
    public TvSerie findById(int id) {
        return tmdbTvSerieMapper.toDomain(tmdbClient.getSeriesById(id));
    }
}
