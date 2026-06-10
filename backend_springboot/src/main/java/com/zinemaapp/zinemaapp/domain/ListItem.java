package com.zinemaapp.zinemaapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "list_items")
public class ListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tmdb_id")
    private Long tmdbId;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListUser list;

    public ListItem() {
    }

    public ListItem(Long id, Long tmdbId, String type, ListUser list) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.type = type;
        this.list = list;
    }

    public ListItem(Long tmdbId, String type, ListUser list) {
        this.tmdbId = tmdbId;
        this.type = type;
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

    public ListUser getList() {
        return list;
    }

    public void setList(ListUser list) {
        this.list = list;
    }
}
