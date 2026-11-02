package com.zinemaapp.ui.model

import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.model.TvSerie

sealed class Media {
    data class MediaFilm(
        val film: Film
    ) : Media()

    data class MediaSerie(
        val serie: TvSerie
    ) : Media()
}