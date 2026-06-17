package com.example.proyecto_gestion_peliculas.domain.usecase.lists

import com.example.proyecto_gestion_peliculas.domain.model.ListItemResponse
import com.example.proyecto_gestion_peliculas.domain.repository.ListsRepository
import javax.inject.Inject

class GetItemsUseCase  @Inject constructor(private val repository: ListsRepository){
    suspend operator fun invoke(listId : Long) : List<ListItemResponse>{
        return repository.getItems(listId)
    }
}