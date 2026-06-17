package com.example.proyecto_gestion_peliculas.domain.usecase.lists

import com.example.proyecto_gestion_peliculas.domain.model.ListResponse
import com.example.proyecto_gestion_peliculas.domain.repository.ListsRepository
import javax.inject.Inject

class GetListsUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke() : List<ListResponse>{
        return repository.getLists()
    }
}