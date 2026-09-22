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
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetFilmsByGenreUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetTopRatedFilmsPagingUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetFilmGenresUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetTvSeriesUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetTopRatedSeriesPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getTopRatedFilmsPagingUseCase: GetTopRatedFilmsPagingUseCase,
    private val getTopRatedSeriesPagingUseCase: GetTopRatedSeriesPagingUseCase,
    private val getFilmGenresUseCase: GetFilmGenresUseCase,
    private val getTvSeriesGenresUseCase: GetTvSeriesUseCase,
    private val getFilmsByGenreUseCase: GetFilmsByGenreUseCase
) : ViewModel() {
    var selectedTab by mutableIntStateOf(0)
        private set
    var filmGenres by mutableStateOf<List<Genre>>(emptyList())
        private set
    var tvSeriesGenres by mutableStateOf<List<Genre>>(emptyList())
        private set
    var films by mutableStateOf<Flow<PagingData<Film>>?>(null)
        private set

    var selectedGenreId by mutableStateOf<Int?>(null)
        private set

    val series = getTopRatedSeriesPagingUseCase().cachedIn(viewModelScope)

    fun selectTab(index: Int) {
        selectedTab = index
    }

    fun selectGenre(id: Int?) {
        selectedGenreId = id
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

    fun getFilmsByGenre(genreId: Int) {
        films = getFilmsByGenreUseCase(genreId)
    }
}