package com.zinemaapp.zinemaapp.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListUserEntity> lists;

    public UserEntity() {
    }

    public UserEntity(Long id, String email, String username, String password, List<ListUserEntity> lists) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.lists = lists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ListUserEntity> getLists() {
        return lists;
    }

    public void setLists(List<ListUserEntity> lists) {
        this.lists = lists;
    }
}
