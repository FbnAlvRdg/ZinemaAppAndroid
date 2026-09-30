package com.zinemaapp.zinemaapp.domain.model;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;


import java.util.List;

public class ListUser {
    private int id;
    private String name;
    private User user;
    private List<ListItem> items;

    public ListUser(int id, String name, User user, List<ListItem> items) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ListItem> getItems() {
        return items;
    }

    public void setItems(List<ListItem> items) {
        this.items = items;
    }
}
