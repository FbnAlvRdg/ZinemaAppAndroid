package com.example.proyecto_gestion_peliculas.ui.features.explore

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetTopRatedFilmsPagingUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetFilmGenresUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetTvSeriesUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetTopRatedSeriesPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getTopRatedFilmsPagingUseCase: GetTopRatedFilmsPagingUseCase,
    private val getTopRatedSeriesPagingUseCase: GetTopRatedSeriesPagingUseCase,
    private val getFilmGenresUseCase: GetFilmGenresUseCase,
    private val getTvSeriesGenresUseCase: GetTvSeriesUseCase
) : ViewModel() {
    var selectedTab by mutableIntStateOf(0)
        private set
    var filmGenres by mutableStateOf<List<Genre>>(emptyList())
        private  set
    var tvSeriesGenres by mutableStateOf<List<Genre>>(emptyList())
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }

    val films = getTopRatedFilmsPagingUseCase().cachedIn(viewModelScope)
    val series = getTopRatedSeriesPagingUseCase().cachedIn(viewModelScope)

    fun logOut(onFinished: () -> Unit) {
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }

    suspend fun getFilmGenres() {
        viewModelScope.launch {
            filmGenres = getFilmGenresUseCase()
        }
    }

    suspend fun getTvSeriesGenres(){
        viewModelScope.launch {
            tvSeriesGenres = getTvSeriesGenresUseCase()
        }
    }
}