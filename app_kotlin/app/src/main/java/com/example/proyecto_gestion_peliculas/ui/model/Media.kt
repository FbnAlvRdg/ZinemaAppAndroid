package com.example.proyecto_gestion_peliculas.ui.model

import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie

sealed class Media {
    data class MediaFilm(
        val film: Film
    ) : Media()

    data class MediaSerie(
        val serie: TvSerie
    ) : Media()
}