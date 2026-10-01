package com.zinemaapp.zinemaapp.application.usecase.tvserie;

import com.zinemaapp.zinemaapp.domain.model.TvSerie;
import com.zinemaapp.zinemaapp.domain.repository.TvSerieRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTvSerieByIdUseCase {
    private final TvSerieRepository tvSerieRepository;

    public GetTvSerieByIdUseCase(TvSerieRepository tvSerieRepository) {
        this.tvSerieRepository = tvSerieRepository;
    }

    public TvSerie getTvSerieById(int id) {
        return tvSerieRepository.findById(id);
    }
}
