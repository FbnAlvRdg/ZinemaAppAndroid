package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.domain.User;
import com.zinemaapp.zinemaapp.dto.internal.login.LoginRequestDTO;
import com.zinemaapp.zinemaapp.dto.internal.signup.RegisterRequestDTO;
import com.zinemaapp.zinemaapp.dto.internal.UserResponseDTO;
import com.zinemaapp.zinemaapp.dto.internal.login.LoginResponseDTO;
import com.zinemaapp.zinemaapp.repository.UserRepository;
import com.zinemaapp.zinemaapp.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El email ya se encuentra registrado"
            );
        }

        if (userRepository.existsByUsername(registerRequestDTO.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El nombre de usuario ya se encuentra registrado"
            );
        }

        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        User userSaved = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userSaved.getId());
        userResponseDTO.setEmail(userSaved.getEmail());
        userResponseDTO.setUsername(userSaved.getUsername());

        return userResponseDTO;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Optional<User> user = userRepository.findByEmail(loginRequestDTO.getEmail());

        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "El email no es correcto"
            );
        }

        User userToResponse = user.get();

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userToResponse.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "La contraseña no es correcta"
            );
        }

        String token = jwtService.generateToken(userToResponse);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userToResponse.getId());
        userResponseDTO.setUsername(userToResponse.getUsername());
        userResponseDTO.setEmail(userToResponse.getEmail());

        return new LoginResponseDTO(token, userResponseDTO);
    }

    public UserResponseDTO getCurrentUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("El email no se ha encontrado");
        }

        User user = userOptional.get();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUsername(user.getUsername());

        return userResponseDTO;
    }
}
