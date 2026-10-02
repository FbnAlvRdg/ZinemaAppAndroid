package com.zinemaapp.zinemaapp.presentation.mapper;

import com.zinemaapp.zinemaapp.domain.model.auth.LoginRequest;
import com.zinemaapp.zinemaapp.domain.model.auth.LoginResponse;
import com.zinemaapp.zinemaapp.presentation.dto.login.LoginRequestDTO;
import com.zinemaapp.zinemaapp.presentation.dto.login.LoginResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {
    private final UserMapper userMapper;

    public LoginMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public LoginRequest toDomain(LoginRequestDTO loginRequestDTO) {
        return new LoginRequest(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );
    }

    public LoginResponseDTO toDTO(LoginResponse loginResponse) {
        return new LoginResponseDTO(
                loginResponse.getToken(),
                userMapper.toDTO(loginResponse.getUser())
        );
    }
}
