package com.zinemaapp.zinemaapp.domain.model;

public class Actor {
    private int id;
    private String name;
    private String character;

    public Actor(int id, String name, String character) {
        this.id = id;
        this.name = name;
        this.character = character;
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

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
