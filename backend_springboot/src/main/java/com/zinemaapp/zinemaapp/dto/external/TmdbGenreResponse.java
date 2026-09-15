package com.zinemaapp.zinemaapp.dto.external;

import java.util.List;

public class TmdbGenreResponse {
    private List<TmdbGenre> genres;

    public TmdbGenreResponse() {
    }

    public List<TmdbGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<TmdbGenre> genres) {
        this.genres = genres;
    }
}
