package com.example.proyecto_gestion_peliculas.ui.features.toprated

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetTopRatedFilmsPagingUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetTopRatedSeriesPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getTopRatedFilmsPagingUseCase: GetTopRatedFilmsPagingUseCase,
    private val getTopRatedSeriesPagingUseCase: GetTopRatedSeriesPagingUseCase
) : ViewModel() {

    var header = "Top Rated"
    var selectedTab by mutableIntStateOf(0)
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }

    val films = getTopRatedFilmsPagingUseCase().cachedIn(viewModelScope)
    val series = getTopRatedSeriesPagingUseCase().cachedIn(viewModelScope)

    fun logOut(onFinished: () -> Unit){
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }
}