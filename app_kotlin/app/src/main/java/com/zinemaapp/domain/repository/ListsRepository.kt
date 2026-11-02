package com.zinemaapp.domain.repository

import com.zinemaapp.data.remote.dto.lists.AddItemRequest
import com.zinemaapp.data.remote.dto.lists.CreateListRequest
import com.zinemaapp.domain.model.ListItemResponse
import com.zinemaapp.domain.model.ListResponse

interface ListsRepository  {
    suspend fun createList(createListRequest: CreateListRequest): ListResponse
    suspend fun getLists(): List<ListResponse>
    suspend fun addItems(listId: Long, addItemRequest: AddItemRequest): Boolean
    suspend fun getItems(listId: Long): List<ListItemResponse>
    suspend fun deleteList(listId: Long): Boolean
    suspend fun deleteItem(listId: Long, itemId: Long): Boolean
}