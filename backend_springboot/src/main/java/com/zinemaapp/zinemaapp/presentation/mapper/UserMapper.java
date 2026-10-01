package com.zinemaapp.zinemaapp.presentation.mapper;

import com.zinemaapp.zinemaapp.domain.model.User;
import com.zinemaapp.zinemaapp.presentation.dto.signup.UserResponseDTO;

public class UserMapper {
    public UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
