package com.zinemaapp.zinemaapp.infrastructure.persistence.mapper;

import com.zinemaapp.zinemaapp.domain.model.ListItem;
import com.zinemaapp.zinemaapp.domain.model.ListUser;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ListItemMapper {
    private final ListUserMapper listUserMapper;

    public ListItemMapper(@Lazy ListUserMapper listUserMapper) {
        this.listUserMapper = listUserMapper;
    }

    public ListItem toDomain(ListItemEntity listItemEntity){
        ListUser listUser = listUserMapper.toDomain(listItemEntity.getList());
        return new ListItem(
                listItemEntity.getId(),
                listItemEntity.getTmdbId(),
                listItemEntity.getType(),
                listItemEntity.getTitle(),
                listItemEntity.getPoster(),
                listUser
        );
    }
}

