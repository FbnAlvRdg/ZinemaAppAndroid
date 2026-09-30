package com.zinemaapp.zinemaapp.infrastructure.persistence.mapper;

import com.zinemaapp.zinemaapp.domain.model.ListItem;
import com.zinemaapp.zinemaapp.domain.model.ListUser;
import com.zinemaapp.zinemaapp.domain.model.User;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;

import java.util.List;


public class ListUserMapper {
    private final UserMapper userMapper;
    private final ListItemMapper listItemMapper;

    public ListUserMapper(UserMapper userMapper, ListItemMapper listItemMapper) {
        this.userMapper = userMapper;
        this.listItemMapper = listItemMapper;
    }

    public ListUser toDomain(ListUserEntity listUserEntity) {
        User user = userMapper.toDomain(listUserEntity.getUser());
        List<ListItem> items = listUserEntity.getItems()
                .stream()
                .map(listItemMapper::toDomain)
                .toList();

        return new ListUser(
                listUserEntity.getId(),
                listUserEntity.getName(),
                user,
                items
        );
    }
}
