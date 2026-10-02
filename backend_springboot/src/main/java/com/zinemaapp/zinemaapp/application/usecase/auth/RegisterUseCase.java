package com.zinemaapp.zinemaapp.application.usecase.auth;

import com.zinemaapp.zinemaapp.domain.model.auth.RegisterRequest;
import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.domain.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegisterUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ese email ya se encuentra registrado"
            );
        }

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ese nombre de usuario ya se encuentra registrado"
            );
        }

        String password = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User(
                null,
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                password
        );

        return userRepository.save(user);
    }
}
