package com.zinemaapp.data.repository

import com.zinemaapp.data.remote.datasource.lists.ListDataSource
import com.zinemaapp.data.remote.dto.lists.AddItemRequest
import com.zinemaapp.data.remote.dto.lists.CreateListRequest
import com.zinemaapp.data.remote.mapper.toDomain
import com.zinemaapp.domain.model.ListItemResponse
import com.zinemaapp.domain.model.ListResponse
import com.zinemaapp.domain.repository.ListsRepository
import javax.inject.Inject
import kotlin.collections.map

class ListsRepositoryImpl @Inject constructor(private val dataSource: ListDataSource) :
    ListsRepository {
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
        return dataSource.getItems(listId)
            .map { listItemResponseDTO -> listItemResponseDTO.toDomain() }
    }

    override suspend fun deleteList(listId: Long): Boolean {
        return dataSource.deleteList(listId)
    }

    override suspend fun deleteItem(listId: Long, itemId: Long): Boolean {
        return dataSource.deleteItem(listId, itemId)
    }
}