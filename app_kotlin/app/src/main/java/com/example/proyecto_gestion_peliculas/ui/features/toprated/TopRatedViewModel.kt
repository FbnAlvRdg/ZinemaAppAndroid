package com.example.proyecto_gestion_peliculas.ui.features.toprated

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.proyecto_gestion_peliculas.core.error.Error
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetTopRatedFilmsUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetTopRatedSeriesPagingUseCase
import com.example.proyecto_gestion_peliculas.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getTopRatedFilmsUseCase: GetTopRatedFilmsUseCase,
    private val getTopRatedSeriesUseCase: GetTopRatedSeriesPagingUseCase
) : ViewModel() {

    var header = "Top Rated"
    var selectedTab by mutableIntStateOf(0)
        private set
    var films by mutableStateOf<Flow<PagingData<Film>>?>(emptyFlow())
        private set
    var series by mutableStateOf<Flow<PagingData<TvSerie>>?>(emptyFlow())
        private set
    var uiState by mutableStateOf(UiState())
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }

    fun getTopRatedFilms() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                films = getTopRatedFilmsUseCase()
            } catch (e: IOException) {
                uiState = uiState.copy(
                    error = Error.CONNECTION_ERROR
                )
            } catch (e: HttpException) {
                uiState = uiState.copy(
                    error = when (e.code()) {
                        404 -> Error.NOT_FOUND
                        in 500..599 -> Error.SERVER_ERROR
                        else -> Error.UNKNOWN
                    }
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = Error.UNKNOWN
                )
            } finally {
                uiState = uiState.copy(
                    isLoading = false
                )
            }
        }
    }

    fun getTopRatedSeries() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                series = getTopRatedSeriesUseCase()
            } catch (e: IOException) {
                uiState = uiState.copy(
                    error = Error.CONNECTION_ERROR
                )
            } catch (e: HttpException) {
                uiState = uiState.copy(
                    error = when (e.code()) {
                        404 -> Error.NOT_FOUND
                        in 500..599 -> Error.SERVER_ERROR
                        else -> Error.UNKNOWN
                    }
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = Error.UNKNOWN
                )
            } finally {
                uiState = uiState.copy(
                    isLoading = false
                )
            }
        }
    }

    fun cleanError() {
        uiState = uiState.copy(
            error = null
        )
    }

    fun logOut(onFinished: () -> Unit) {
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }
}