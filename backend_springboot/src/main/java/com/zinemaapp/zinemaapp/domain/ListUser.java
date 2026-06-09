package com.zinemaapp.zinemaapp.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lists")
public class ListUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_table")
    private int id;

    @Column(name = "name_list")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListItem> items;

    public ListUser() {
    }

    public ListUser(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

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
