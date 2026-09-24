package com.example.proyecto_gestion_peliculas.ui.features.lists

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.CreateListRequest
import com.example.proyecto_gestion_peliculas.domain.model.ListResponse
import com.example.proyecto_gestion_peliculas.domain.usecase.lists.CreateListUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.lists.DeleteListUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.lists.GetListsUseCase
import com.example.proyecto_gestion_peliculas.ui.uistate.UiState
import com.example.proyecto_gestion_peliculas.core.error.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getListsUseCase: GetListsUseCase,
    private val createListUseCase: CreateListUseCase,
    private val deleteListUseCase: DeleteListUseCase
) : ViewModel() {

    val header = "Listas"
    var lists by mutableStateOf<List<ListResponse>>(emptyList())
    var uiState by mutableStateOf(UiState())
        private set

    init {
        loadLists()
    }

    fun loadLists() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )

                lists = getListsUseCase()

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

    fun createList(name: String) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )

                val createListRequest = CreateListRequest(
                    name = name
                )

                createListUseCase(createListRequest)
                loadLists()
            } catch (e: IOException) {
                uiState = uiState.copy(
                    error = Error.CONNECTION_ERROR
                )
            } catch (e: HttpException) {
                uiState = uiState.copy(
                    error = when (e.code()) {
                        409 -> Error.CONFLICT
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

    fun deleteList(listId: Long) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )

                deleteListUseCase(listId)
                loadLists()
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

    fun logOut(onFinished: () -> Unit) {
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }

    fun cleanError() {
        uiState = uiState.copy(
            error = null
        )
    }
}