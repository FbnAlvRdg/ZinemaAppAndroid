package com.zinemaapp.zinemaapp.domain.repository.user;

import com.zinemaapp.zinemaapp.domain.model.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User save(User user);
}
