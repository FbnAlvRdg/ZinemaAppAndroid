package com.zinemaapp.zinemaapp.domain.repository.lists;

import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ListItemRepository {
    boolean existsByListAndTmdbId(Long listId, Long tmdbId);

    ListItem save(ListItem listItem);

    List<ListItem> findByList(Long listId);

    ListItem findById(Long itemId);

    boolean delete(ListItem listItem);
}
