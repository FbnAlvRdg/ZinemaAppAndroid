package com.zinemaapp.zinemaapp.dto.internal.lists;

public class CreateListRequest {
    private String name;

    public CreateListRequest() {
    }

    public CreateListRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
