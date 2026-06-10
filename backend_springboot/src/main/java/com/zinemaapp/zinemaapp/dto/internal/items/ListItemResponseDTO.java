package com.zinemaapp.zinemaapp.dto.internal.items;

public class ListItemResponseDTO {
    private Long id;
    private Long tmdbId;
    private String type;

    public ListItemResponseDTO() {
    }

    public ListItemResponseDTO(Long id, Long tmdbId, String type) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.type = type;
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
}
