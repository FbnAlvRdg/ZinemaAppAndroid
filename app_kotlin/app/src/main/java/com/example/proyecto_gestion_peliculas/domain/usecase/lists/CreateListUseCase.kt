package com.example.proyecto_gestion_peliculas.domain.usecase.lists

import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.CreateListRequest
import com.example.proyecto_gestion_peliculas.domain.model.ListResponse
import com.example.proyecto_gestion_peliculas.domain.repository.ListsRepository
import javax.inject.Inject

class CreateListUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(createListRequest: CreateListRequest) : ListResponse{
        return repository.createList(createListRequest)
    }
}