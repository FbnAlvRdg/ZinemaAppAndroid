package com.example.proyecto_gestion_peliculas.data.remote.api

import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.AddItemRequest
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.CreateListRequest
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListItemResponseDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListResponseDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ListApi {
    @GET("/lists")
    suspend fun getLists(): List<ListResponseDTO>

    @POST("/lists")
    suspend fun createList(@Body createListRequest: CreateListRequest): ListResponseDTO

    @POST("/lists/{listId}/items")
    suspend fun addItems(
        @Path("listId") listId: Long,
        @Body addItemRequest: AddItemRequest
    ): Boolean

    @GET("/lists/{listId}/items")
    suspend fun getItems(@Path("listId") listId: Long): List<ListItemResponseDTO>

    @DELETE("/lists/{listId}")
    suspend fun deleteList(@Path("listId") listId: Long): Boolean

    @DELETE("/lists/{listId}/items/{itemId}")
    suspend fun deleteItem(
        @Path("listId") listId: Long,
        @Path("itemId") itemId: Long
    ): Boolean
}