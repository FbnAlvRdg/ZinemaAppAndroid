package com.zinemaapp.zinemaapp.dto.internal.items;

public class ListItemResponseDTO {
    private Long id;
    private Long tmdbId;
    private String type;
    private String title;
    private String poster;

    public ListItemResponseDTO() {
    }

    public ListItemResponseDTO(Long id, Long tmdbId, String type, String title, String poster) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.type = type;
        this.title = title;
        this.poster = poster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
