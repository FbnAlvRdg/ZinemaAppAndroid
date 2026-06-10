package com.example.proyecto_gestion_peliculas.data.remote.datasource.lists

import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.AddItemRequest
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.CreateListRequest
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListItemResponseDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListResponseDTO

interface ListDataSource {
    suspend fun createList(createListRequest: CreateListRequest): ListResponseDTO
    suspend fun getLists(): List<ListResponseDTO>
    suspend fun addItems(listId: Long, addItemRequest: AddItemRequest): Boolean
    suspend fun getItems(listId: Long): List<ListItemResponseDTO>
    suspend fun deleteList(listId: Long): Boolean
    suspend fun deleteItem(listId: Long, itemId: Long): Boolean
}