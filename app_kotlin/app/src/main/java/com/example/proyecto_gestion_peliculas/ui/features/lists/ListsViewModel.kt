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
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getListsUseCase: GetListsUseCase,
    private val createListUseCase: CreateListUseCase,
    private val deleteListUseCase: DeleteListUseCase
) : ViewModel() {

    var lists by mutableStateOf<List<ListResponse>>(emptyList())
    var showDialog by mutableStateOf(false)
    val header = "Listas"

    init {
        loadLists()
    }

    fun loadLists() {
        viewModelScope.launch {
            lists = getListsUseCase()
        }
    }

    fun createList(name: String) {
        viewModelScope.launch {
            val createListRequest = CreateListRequest(
                name = name
            )
            createListUseCase(createListRequest)
            loadLists()
        }
    }

    fun deleteList(listId: Long) {
        viewModelScope.launch {
            deleteListUseCase(listId)
            loadLists()
        }
    }

    fun logOut(onFinished: () -> Unit) {
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }
}