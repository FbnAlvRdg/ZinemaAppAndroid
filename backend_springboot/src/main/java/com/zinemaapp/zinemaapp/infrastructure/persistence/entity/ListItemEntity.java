package com.zinemaapp.zinemaapp.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "list_items")
public class ListItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tmdb_id")
    private Long tmdbId;

    @Column(name = "type")
    private String type;

    private String title;
    private String poster;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListUserEntity list;

    public ListItemEntity() {
    }

    public ListItemEntity(Long id, Long tmdbId, String type, ListUserEntity list) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.type = type;
        this.list = list;
    }

    public ListItemEntity(Long tmdbId, String type, ListUserEntity list) {
        this.tmdbId = tmdbId;
        this.type = type;
        this.list = list;
    }

    public ListItemEntity(Long tmdbId, String type, String title, String poster, ListUserEntity list) {
        this.tmdbId = tmdbId;
        this.type = type;
        this.title = title;
        this.poster = poster;
        this.list = list;
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

    public ListUserEntity getList() {
        return list;
    }

    public void setList(ListUserEntity list) {
        this.list = list;
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
