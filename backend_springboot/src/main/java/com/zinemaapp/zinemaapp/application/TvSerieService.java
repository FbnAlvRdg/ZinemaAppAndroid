package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.tvserie.TmdbTvSerieResponse;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.tvserie.TmdbTvSeriesResponse;
import com.zinemaapp.zinemaapp.dto.internal.TvSerieDTO;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.client.TmdbClient;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.mapper.TmdbTvSerieMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TvSerieService {
    private final TmdbClient tmdbClient;
    private final TmdbTvSerieMapper tmdbTvSerieMapper;

    public TvSerieService(TmdbClient tmdbClient, TmdbTvSerieMapper tmdbTvSerieMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbTvSerieMapper = tmdbTvSerieMapper;
    }

    public TvSerieDTO getSerieById(int id) {
        TmdbTvSerieResponse tmdbTvSerieResponse = tmdbClient.getSeriesById(id);
        return tmdbTvSerieMapper.toTvSerieDTO(tmdbTvSerieResponse);
    }

    public List<TvSerieDTO> getTopRatedSeries(int page) {
        TmdbTvSeriesResponse tmdbTvSeriesResponse = tmdbClient.getTopRatedSeries(page);

        List<TvSerieDTO> series = new ArrayList<>();

        for (TmdbTvSerieResponse tmdbTvSerieResponse : tmdbTvSeriesResponse.getResults()) {
            series.add(tmdbTvSerieMapper.toTvSerieDTO(tmdbTvSerieResponse));
        }

        return series;
    }

    public List<TvSerieDTO> getMostPopularSeries(int page) {
        TmdbTvSeriesResponse tmdbTvSeriesResponse = tmdbClient.getMostPopularSeries(page);

        List<TvSerieDTO> series = new ArrayList<>();

        for (TmdbTvSerieResponse tmdbTvSerieResponse : tmdbTvSeriesResponse.getResults()) {
            series.add(tmdbTvSerieMapper.toTvSerieDTO(tmdbTvSerieResponse));
        }

        return series;
    }

    public List<TvSerieDTO> getSeriesByGenre(int idGenre, int page) {
        TmdbTvSeriesResponse tmdbTvSeriesResponse = tmdbClient.getSeriesByGenre(idGenre, page);

        List<TvSerieDTO> series = new ArrayList<>();

        for (TmdbTvSerieResponse tmdbTvSerieResponse : tmdbTvSeriesResponse.getResults()) {
            series.add(tmdbTvSerieMapper.toTvSerieDTO(tmdbTvSerieResponse));
        }

        return series;
    }
}
