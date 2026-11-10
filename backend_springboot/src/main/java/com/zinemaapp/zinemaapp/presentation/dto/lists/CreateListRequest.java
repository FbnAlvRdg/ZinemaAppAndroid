package com.zinemaapp.zinemaapp.presentation.dto.lists;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateListRequest {
    @NotBlank
    @Size(max = 100)
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
