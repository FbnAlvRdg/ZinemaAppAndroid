package com.zinemaapp.data.remote.dto.lists

import android.icu.text.CaseMap

data class ListItemResponseDTO(
    val id: Long,
    val tmdbId: Long,
    val type: String,
    val title: String?,
    val poster : String?
)
