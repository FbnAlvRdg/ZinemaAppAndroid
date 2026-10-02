package com.zinemaapp.zinemaapp.infrastructure.persistence.mapper;

import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListUserEntityMapper {
    private final UserEntityMapper userEntityMapper;
    private final ListItemEntityMapper listItemEntityMapper;

    public ListUserEntityMapper(@Lazy UserEntityMapper userEntityMapper, ListItemEntityMapper listItemEntityMapper) {
        this.userEntityMapper = userEntityMapper;
        this.listItemEntityMapper = listItemEntityMapper;
    }

    public ListUser toDomain(ListUserEntity listUserEntity) {
        User user = userEntityMapper.toDomain(listUserEntity.getUser());
        List<ListItem> items = listUserEntity.getItems()
                .stream()
                .map(listItemEntityMapper::toDomain)
                .toList();

        return new ListUser(
                listUserEntity.getId(),
                listUserEntity.getName(),
                user,
                items
        );
    }

    public ListUserEntity toEntity(ListUser listUser) {
        UserEntity userEntity = userEntityMapper.toEntity(listUser.getUser());
        List<ListItemEntity> itemsEntity = listUser.getItems()
                .stream()
                .map(listItemEntityMapper::toEntity)
                .toList();

        return new ListUserEntity(
                listUser.getId(),
                listUser.getName(),
                userEntity,
                itemsEntity

        );
    }
}
