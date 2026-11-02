package com.zinemaapp.data.remote.datasource.tvserie

import com.zinemaapp.data.remote.dto.series.TvSerieDTO

interface TvSerieDataSource {
    suspend fun getTopRated(page: Int): List<TvSerieDTO>
    suspend fun getMostPopular(page: Int): List<TvSerieDTO>

    suspend fun getSeriesByGenre(genreId : Int, page: Int) : List<TvSerieDTO>
    suspend fun getById(id: Int): TvSerieDTO
}