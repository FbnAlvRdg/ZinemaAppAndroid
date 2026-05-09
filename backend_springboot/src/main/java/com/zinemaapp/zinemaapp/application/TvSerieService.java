package com.zinemaapp.zinemaapp.application;

import com.zinemaapp.zinemaapp.dto.external.tvserie.TmdbTvSerieResponse;
import com.zinemaapp.zinemaapp.dto.external.tvserie.TmdbTvSeriesResponse;
import com.zinemaapp.zinemaapp.dto.internal.TvSerieDTO;
import com.zinemaapp.zinemaapp.infrastructure.TmdbClient;
import com.zinemaapp.zinemaapp.mapper.TvSerieMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TvSerieService {
    private final TmdbClient tmdbClient;
    private final TvSerieMapper tvSerieMapper;

    public TvSerieService(TmdbClient tmdbClient, TvSerieMapper tvSerieMapper) {
        this.tmdbClient = tmdbClient;
        this.tvSerieMapper = tvSerieMapper;
    }

    public List<TvSerieDTO> getTopRatedSerie(int page) {
        TmdbTvSeriesResponse tmdbTvSeriesResponse = tmdbClient.getTopRatedSeries(page);

        List<TvSerieDTO> series = new ArrayList<>();

        for (TmdbTvSerieResponse tmdbTvSerieResponse : tmdbTvSeriesResponse.getResults()) {
            series.add(tvSerieMapper.toTvSerieDTO(tmdbTvSerieResponse));
        }

        return series;
    }
}
