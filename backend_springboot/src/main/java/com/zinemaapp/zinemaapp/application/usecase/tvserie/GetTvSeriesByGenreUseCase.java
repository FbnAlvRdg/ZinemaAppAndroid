package com.zinemaapp.zinemaapp.application.usecase.tvserie;

import com.zinemaapp.zinemaapp.domain.model.TvSerie;
import com.zinemaapp.zinemaapp.domain.repository.TvSerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTvSeriesByGenreUseCase {
    private final TvSerieRepository tvSerieRepository;

    public GetTvSeriesByGenreUseCase(TvSerieRepository tvSerieRepository) {
        this.tvSerieRepository = tvSerieRepository;
    }

    public List<TvSerie> getSeriesByGenre(int genreId, int page) {
        return tvSerieRepository.findByGenre(genreId, page);
    }
}
