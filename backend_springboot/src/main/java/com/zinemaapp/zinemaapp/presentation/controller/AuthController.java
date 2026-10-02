package com.zinemaapp.zinemaapp.presentation.controller;

import com.zinemaapp.zinemaapp.application.usecase.auth.LoginUseCase;
import com.zinemaapp.zinemaapp.application.usecase.auth.RegisterUseCase;
import com.zinemaapp.zinemaapp.domain.model.auth.LoginRequest;
import com.zinemaapp.zinemaapp.domain.model.auth.LoginResponse;
import com.zinemaapp.zinemaapp.domain.model.auth.RegisterRequest;
import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.presentation.dto.login.LoginRequestDTO;
import com.zinemaapp.zinemaapp.presentation.dto.signup.RegisterRequestDTO;
import com.zinemaapp.zinemaapp.presentation.dto.signup.UserResponseDTO;
import com.zinemaapp.zinemaapp.presentation.dto.login.LoginResponseDTO;
import com.zinemaapp.zinemaapp.presentation.mapper.LoginMapper;
import com.zinemaapp.zinemaapp.presentation.mapper.RegisterRequestMapper;
import com.zinemaapp.zinemaapp.presentation.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final RegisterRequestMapper registerRequestMapper;
    private final UserMapper userMapper;
    private final LoginMapper loginMapper;

    public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase, RegisterRequestMapper registerRequestMapper, UserMapper userMapper, LoginMapper loginMapper) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.registerRequestMapper = registerRequestMapper;
        this.userMapper = userMapper;
        this.loginMapper = loginMapper;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        RegisterRequest registerRequest = registerRequestMapper.toDomain(registerRequestDTO);
        User user = registerUseCase.register(registerRequest);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginRequest loginRequest = loginMapper.toDomain(loginRequestDTO);
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(loginMapper.toDTO(loginResponse));
    }
}
