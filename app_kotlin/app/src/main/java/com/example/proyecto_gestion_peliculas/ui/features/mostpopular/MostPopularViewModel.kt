package com.example.proyecto_gestion_peliculas.ui.features.mostpopular

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetMostPopularFilmsPagingUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetMostPopularTvSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getMostPopularFilmsPagingUseCase: GetMostPopularFilmsPagingUseCase,
    private val getMostPopularTvSeriesUseCase: GetMostPopularTvSeriesUseCase
) : ViewModel() {

    var header = "Most Popular"
    var selectedTab by mutableIntStateOf(0)
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }

    val films = getMostPopularFilmsPagingUseCase().cachedIn(viewModelScope)
    val series = getMostPopularTvSeriesUseCase().cachedIn(viewModelScope)

    fun logOut(onFinished : () -> Unit){
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }
}