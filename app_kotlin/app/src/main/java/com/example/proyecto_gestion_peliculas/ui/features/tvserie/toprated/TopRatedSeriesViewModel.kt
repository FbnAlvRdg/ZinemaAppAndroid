package com.example.proyecto_gestion_peliculas.ui.features.tvserie.toprated

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetTopRatedTvSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TopRatedSeriesViewModel @Inject constructor(private val getTopRatedTvSeriesUseCase: GetTopRatedTvSeriesUseCase) :
    ViewModel() {
    var tvSeries by mutableStateOf<List<TvSerie>>(emptyList())
        private set

    var page = 1
        private set

    init {
        loadTvSeries()
    }

    fun loadTvSeries() {
        viewModelScope.launch {
            val newSeries = getTopRatedTvSeriesUseCase(page)
            tvSeries = (tvSeries + newSeries).distinctBy { tvSeries -> tvSeries.id }
            page++
        }
    }

}