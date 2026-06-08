package com.zinemaapp.zinemaapp.domain;

import jakarta.persistence.*;

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

    public ListUser() {
    }

    public ListUser(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
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
}
