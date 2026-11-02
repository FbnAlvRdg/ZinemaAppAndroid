package com.zinemaapp.data.remote.datasource.tvserie

import com.zinemaapp.data.remote.api.TvSerieApi
import com.zinemaapp.data.remote.dto.series.TvSerieDTO
import javax.inject.Inject


class TvSerieDataSourceImpl @Inject constructor(private val api: TvSerieApi) : TvSerieDataSource {
    override suspend fun getTopRated(page: Int): List<TvSerieDTO> {
        return api.getTopRatedSeries(page)
    }

    override suspend fun getMostPopular(page: Int): List<TvSerieDTO> {
        return api.getMostPopular(page)
    }

    override suspend fun getSeriesByGenre(
        genreId: Int,
        page: Int
    ): List<TvSerieDTO> {
        return api.getSeriesByGenre(genreId, page)
    }

    override suspend fun getById(id: Int): TvSerieDTO {
        return api.getSerieById(id)
    }
}