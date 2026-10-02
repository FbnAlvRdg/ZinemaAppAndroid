package com.zinemaapp.zinemaapp.application.usecase.auth;

import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.domain.model.auth.LoginRequest;
import com.zinemaapp.zinemaapp.domain.model.auth.LoginResponse;
import com.zinemaapp.zinemaapp.domain.repository.user.UserRepository;
import com.zinemaapp.zinemaapp.infrastructure.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest loginRequest){
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if (user.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "El email no es correcto"
            );
        }

        User userToLogin = user.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), userToLogin.getPassword())){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "La contraseña no es correcta"
            );
        }

        String token = jwtService.generateToken(userToLogin.getEmail());

        return new LoginResponse(
                token,
                userToLogin
        );
    }
}
