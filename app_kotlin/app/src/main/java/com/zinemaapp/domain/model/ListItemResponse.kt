package com.zinemaapp.domain.model

data class ListItemResponse(
    val id: Long,
    val tmdbId: Long,
    val type: String,
    val title : String?,
    val poster : String?
)
