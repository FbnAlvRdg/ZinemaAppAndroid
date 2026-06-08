package com.zinemaapp.zinemaapp.dto.internal.lists;

public class ListResponseDTO {
    private int id;
    private String name;

    public ListResponseDTO() {
    }

    public ListResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
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
}
