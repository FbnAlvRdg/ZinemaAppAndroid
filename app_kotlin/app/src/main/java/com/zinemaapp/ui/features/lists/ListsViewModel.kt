package com.zinemaapp.ui.features.lists

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinemaapp.data.remote.dto.lists.CreateListRequest
import com.zinemaapp.domain.model.ListResponse
import com.zinemaapp.domain.usecase.lists.CreateListUseCase
import com.zinemaapp.domain.usecase.lists.DeleteListUseCase
import com.zinemaapp.domain.usecase.lists.GetListsUseCase
import com.zinemaapp.ui.uistate.UiState
import com.zinemaapp.core.error.mapper.toAppError
import com.zinemaapp.data.datastore.clearJwt
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
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

            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = e.toAppError()
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

            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = e.toAppError()
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

            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = e.toAppError()
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