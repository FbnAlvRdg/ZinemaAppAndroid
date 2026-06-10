package com.example.proyecto_gestion_peliculas.data.remote.datasource.lists

import com.example.proyecto_gestion_peliculas.data.remote.api.ListApi
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.AddItemRequest
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.CreateListRequest
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListItemResponseDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListResponseDTO
import javax.inject.Inject

class ListDataSourceImpl @Inject constructor(private val listApi: ListApi) : ListDataSource {
    override suspend fun createList(createListRequest: CreateListRequest): ListResponseDTO {
        return listApi.createList(createListRequest)
    }

    override suspend fun getLists(): List<ListResponseDTO> {
        return listApi.getLists()
    }

    override suspend fun addItems(
        listId: Long,
        addItemRequest: AddItemRequest
    ): Boolean {
        return listApi.addItems(listId, addItemRequest)
    }

    override suspend fun getItems(listId: Long): List<ListItemResponseDTO> {
        return listApi.getItems(listId)
    }

    override suspend fun deleteList(listId: Long): Boolean {
        return listApi.deleteList(listId)
    }

    override suspend fun deleteItem(listId: Long, itemId: Long): Boolean {
        return listApi.deleteItem(listId, itemId)
    }
}