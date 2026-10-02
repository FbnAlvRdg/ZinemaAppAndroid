package com.zinemaapp.zinemaapp.presentation.mapper;

import com.zinemaapp.zinemaapp.domain.model.auth.RegisterRequest;
import com.zinemaapp.zinemaapp.presentation.dto.signup.RegisterRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestMapper {
    public RegisterRequest toDomain(RegisterRequestDTO registerRequestDTO) {
        return new RegisterRequest(
                registerRequestDTO.getUsername(),
                registerRequestDTO.getEmail(),
                registerRequestDTO.getPassword()
        );
    }
}
