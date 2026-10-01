package com.zinemaapp.zinemaapp.application.usecase.tvserie;

import com.zinemaapp.zinemaapp.domain.model.TvSerie;
import com.zinemaapp.zinemaapp.domain.repository.TvSerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMostPopularTvSerieUseCase {
    private final TvSerieRepository tvSerieRepository;

    public GetMostPopularTvSerieUseCase(TvSerieRepository tvSerieRepository) {
        this.tvSerieRepository = tvSerieRepository;
    }

    public List<TvSerie> getMostPopularSeries(int page){
        return tvSerieRepository.findMostPopular(page);
    }
}
