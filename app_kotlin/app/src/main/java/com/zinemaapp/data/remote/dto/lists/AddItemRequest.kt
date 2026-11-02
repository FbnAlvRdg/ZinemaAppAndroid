package com.zinemaapp.data.remote.dto.lists

data class AddItemRequest(
    val tmdbId : Long,
    val type : String,
    val title : String,
    val poster : String?
)
