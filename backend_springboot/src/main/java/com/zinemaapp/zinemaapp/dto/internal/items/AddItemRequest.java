package com.zinemaapp.zinemaapp.dto.internal.items;

public class AddItemRequest {
    private Long tmdbId;
    private String type;

    public AddItemRequest() {
    }

    public AddItemRequest(Long tmdbId, String type) {
        this.tmdbId = tmdbId;
        this.type = type;
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
