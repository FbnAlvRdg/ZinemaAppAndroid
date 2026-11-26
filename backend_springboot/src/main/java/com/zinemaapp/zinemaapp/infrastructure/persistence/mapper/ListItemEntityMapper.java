package com.zinemaapp.zinemaapp.infrastructure.persistence.mapper;

import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListItemEntityMapper {
    private final ListUserEntityMapper listUserEntityMapper;
    private final UserEntityMapper userEntityMapper;

    public ListItemEntityMapper(@Lazy ListUserEntityMapper listUserEntityMapper, UserEntityMapper userEntityMapper) {
        this.listUserEntityMapper = listUserEntityMapper;
        this.userEntityMapper = userEntityMapper;
    }

    public ListItem toDomain(ListItemEntity listItemEntity) {
        ListUserEntity listUserEntity = listItemEntity.getList();

        ListUser listUser = new ListUser(
                listUserEntity.getId(),
                listUserEntity.getName(),
                userEntityMapper.toDomain(listUserEntity.getUser()),
                List.of()
        );

        return new ListItem(
                listItemEntity.getId(),
                listItemEntity.getTmdbId(),
                listItemEntity.getType(),
                listItemEntity.getTitle(),
                listItemEntity.getPoster(),
                listUser
        );
    }

    public ListItemEntity toEntity(ListItem listItem) {
        ListUserEntity listUserEntity = listUserEntityMapper.toEntity(listItem.getList());

        ListItemEntity listItemEntity = new ListItemEntity(
                listItem.getTmdbId(),
                listItem.getType(),
                listItem.getTitle(),
                listItem.getPoster(),
                listUserEntity
        );

        listItemEntity.setId(listItem.getId());
        return listItemEntity;
    }
}

