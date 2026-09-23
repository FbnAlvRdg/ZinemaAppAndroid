package com.example.proyecto_gestion_peliculas.ui.features.explore

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.core.error.Error
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetFilmsByGenreUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetMostPopularFilmsUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetFilmGenresUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.genre.GetTvSeriesGenresUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetMostPopularTvSeriesUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetSeriesByGenreUseCase
import com.example.proyecto_gestion_peliculas.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getMostPopularFilmsUseCase: GetMostPopularFilmsUseCase,
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
    var uiState by mutableStateOf(UiState())
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }
    fun getFilmGenres() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                filmGenres = getFilmGenresUseCase()
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

    fun getTvSeriesGenres() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                tvSeriesGenres = getTvSeriesGenresUseCase()
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

    fun loadInitialFilms() {
        try {
            initialFilms = getMostPopularFilmsUseCase()
            films = initialFilms
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

    fun loadInitialTvSeries() {
        try {
            uiState = uiState.copy(
                isLoading = true
            )
            initialTvSeries = getMostPopularTvSeriesUseCase()
            tvSeries = initialTvSeries
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

    fun getFilmsByGenre(genreId: Int) {
        try {
            uiState = uiState.copy(
                isLoading = true
            )
            films = getFilmsByGenreUseCase(genreId)
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

    fun getSeriesByGenre(genreId: Int) {
        try {
            uiState = uiState.copy(
                isLoading = true
            )
            tvSeries = getSeriesByGenreUseCase(genreId)
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