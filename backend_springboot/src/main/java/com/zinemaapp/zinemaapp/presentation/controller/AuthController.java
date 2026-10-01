package com.zinemaapp.zinemaapp.presentation.controller;

import com.zinemaapp.zinemaapp.presentation.dto.login.LoginRequestDTO;
import com.zinemaapp.zinemaapp.presentation.dto.signup.RegisterRequestDTO;
import com.zinemaapp.zinemaapp.presentation.dto.signup.UserResponseDTO;
import com.zinemaapp.zinemaapp.presentation.dto.login.LoginResponseDTO;
import com.zinemaapp.zinemaapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }

    @GetMapping("/me")
    public UserResponseDTO me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;
        if (authentication != null){
            email = authentication.getName();
        }

        return userService.getCurrentUser(email);
    }
}
