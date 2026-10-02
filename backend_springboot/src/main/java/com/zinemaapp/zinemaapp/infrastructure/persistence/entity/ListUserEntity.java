package com.zinemaapp.zinemaapp.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lists")
public class ListUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_table")
    private int id;

    @Column(name = "name_list")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListItemEntity> items = new ArrayList<>();

    public ListUserEntity() {
    }

    public ListUserEntity(int id, String name, UserEntity userEntity) {
        this.id = id;
        this.name = name;
        this.userEntity = userEntity;
    }

    public ListUserEntity(int id, String name, UserEntity userEntity, List<ListItemEntity> items) {
        this.id = id;
        this.name = name;
        this.userEntity = userEntity;
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

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<ListItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ListItemEntity> items) {
        this.items = items;
    }
}
