package com.example.proyecto_gestion_peliculas.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class FilmDTO(
    val id : Int,
    val title : String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("overview")
    val synopsis : String,
    @SerializedName("poster_path")
    val poster : String,
    val rating : Double
    )
