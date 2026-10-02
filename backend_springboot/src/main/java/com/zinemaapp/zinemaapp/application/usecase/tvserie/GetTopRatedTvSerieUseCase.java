package com.zinemaapp.zinemaapp.application.usecase.tvserie;

import com.zinemaapp.zinemaapp.domain.model.tvserie.TvSerie;
import com.zinemaapp.zinemaapp.domain.repository.tvseries.TvSerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTopRatedTvSerieUseCase {
    private final TvSerieRepository tvSerieRepository;

    public GetTopRatedTvSerieUseCase(TvSerieRepository tvSerieRepository) {
        this.tvSerieRepository = tvSerieRepository;
    }

    public List<TvSerie> getTopRatedSeries(int page){
        return tvSerieRepository.findTopRated(page);
    }
}
