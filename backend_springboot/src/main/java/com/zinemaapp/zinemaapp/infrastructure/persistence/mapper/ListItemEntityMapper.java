package com.zinemaapp.zinemaapp.infrastructure.persistence.mapper;

import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ListItemEntityMapper {
    private final ListUserEntityMapper listUserEntityMapper;

    public ListItemEntityMapper(@Lazy ListUserEntityMapper listUserEntityMapper) {
        this.listUserEntityMapper = listUserEntityMapper;
    }

    public ListItem toDomain(ListItemEntity listItemEntity) {
        return new ListItem(
                listItemEntity.getId(),
                listItemEntity.getTmdbId(),
                listItemEntity.getType(),
                listItemEntity.getTitle(),
                listItemEntity.getPoster()
        );
    }

    public ListItemEntity toEntity(ListItem listItem) {
        ListUserEntity listUserEntity = listUserEntityMapper.toEntity(listItem.getList());
        return new ListItemEntity(
                listItem.getTmdbId(),
                listItem.getType(),
                listItem.getTitle(),
                listItem.getPoster(),
                listUserEntity
        );
    }
}

