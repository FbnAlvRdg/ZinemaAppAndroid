package com.example.proyecto_gestion_peliculas.ui.features.explore

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetFilmsByGenreUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetMostPopularFilmsPagingUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetFilmGenresUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetTvSeriesGenresUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetMostPopularTvSeriesUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetSeriesByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getMostPopularFilmsPagingUseCase: GetMostPopularFilmsPagingUseCase,
    private val getMostPopularTvSeriesUseCase: GetMostPopularTvSeriesUseCase,
    private val getFilmGenresUseCase: GetFilmGenresUseCase,
    private val getTvSeriesGenresUseCase: GetTvSeriesGenresUseCase,
    private val getFilmsByGenreUseCase: GetFilmsByGenreUseCase,
    private val getSeriesByGenreUseCase: GetSeriesByGenreUseCase
) : ViewModel() {
    var selectedTab by mutableIntStateOf(0)
        private set
    var filmGenres by mutableStateOf<List<Genre>>(emptyList())
        private set
    var tvSeriesGenres by mutableStateOf<List<Genre>>(emptyList())
        private set
    var initialFilms by mutableStateOf<Flow<PagingData<Film>>?>(null)
        private set
    var initialTvSeries by mutableStateOf<Flow<PagingData<TvSerie>>?>(null)
        private set
    var films by mutableStateOf<Flow<PagingData<Film>>?>(null)
        private set
    var tvSeries by mutableStateOf<Flow<PagingData<TvSerie>>?>(null)
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }

    fun logOut(onFinished: () -> Unit) {
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }

    fun getFilmGenres() {
        viewModelScope.launch {
            filmGenres = getFilmGenresUseCase()
        }
    }

    fun getTvSeriesGenres() {
        viewModelScope.launch {
            tvSeriesGenres = getTvSeriesGenresUseCase()
        }
    }

    fun loadInitialFilms() {
        initialFilms = getMostPopularFilmsPagingUseCase()
        films = initialFilms
    }

    fun loadInitialTvSeries(){
        initialTvSeries = getMostPopularTvSeriesUseCase()
        tvSeries = initialTvSeries

    }

    fun getFilmsByGenre(genreId: Int) {
        films = getFilmsByGenreUseCase(genreId)
    }

    fun getSeriesByGenre(genreId: Int) {
        tvSeries = getSeriesByGenreUseCase(genreId)
    }
}