package com.example.proyecto_gestion_peliculas.data.repository

import com.example.proyecto_gestion_peliculas.data.remote.datasource.lists.ListDataSource
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.AddItemRequest
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.CreateListRequest
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.ListItemResponse
import com.example.proyecto_gestion_peliculas.domain.model.ListResponse
import com.example.proyecto_gestion_peliculas.domain.repository.ListsRepository
import javax.inject.Inject

class ListsRepositoryImpl @Inject constructor(private val dataSource: ListDataSource) : ListsRepository {
    override suspend fun createList(createListRequest: CreateListRequest): ListResponse {
        return dataSource.createList(createListRequest).toDomain()
    }

    override suspend fun getLists(): List<ListResponse> {
        return dataSource.getLists().map { listResponseDTO -> listResponseDTO.toDomain() }
    }

    override suspend fun addItems(
        listId: Long,
        addItemRequest: AddItemRequest
    ): Boolean {
       return dataSource.addItems(listId, addItemRequest)
    }

    override suspend fun getItems(listId: Long): List<ListItemResponse> {
        return dataSource.getItems(listId).map { listItemResponseDTO -> listItemResponseDTO.toDomain() }
    }

    override suspend fun deleteList(listId: Long): Boolean {
        return dataSource.deleteList(listId)
    }

    override suspend fun deleteItem(listId: Long, itemId: Long): Boolean {
        return dataSource.deleteItem(listId, itemId)
    }
}