package com.zinemaapp.zinemaapp.controller;

import com.zinemaapp.zinemaapp.dto.internal.LoginRequestDTO;
import com.zinemaapp.zinemaapp.dto.internal.RegisterRequestDTO;
import com.zinemaapp.zinemaapp.dto.internal.UserResponseDTO;
import com.zinemaapp.zinemaapp.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }
}
