package com.zinemaapp.zinemaapp.infrastructure.persistence.mapper;

import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntityMapper {

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
