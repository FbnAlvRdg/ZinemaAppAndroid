package com.zinemaapp.zinemaapp.infrastructure.persistence.mapper;

import com.zinemaapp.zinemaapp.domain.model.ListUser;
import com.zinemaapp.zinemaapp.domain.model.User;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    private final ListUserMapper listUserMapper;

    public UserMapper(ListUserMapper listUserMapper) {
        this.listUserMapper = listUserMapper;
    }

    public User toDomain(UserEntity userEntity){
        List<ListUser> listUser = userEntity.getLists().stream().map(listUserMapper::toDomain).toList();
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                listUser
        );
    }
}
