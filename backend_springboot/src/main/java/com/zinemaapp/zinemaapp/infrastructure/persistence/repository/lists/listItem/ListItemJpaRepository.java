package com.zinemaapp.zinemaapp.infrastructure.persistence.repository.lists.listItem;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListItemJpaRepository extends JpaRepository<ListItemEntity, Long> {
    boolean existsByListAndTmdbId(ListUserEntity listUserEntity, Long tmdbId);

    List<ListItemEntity> findByList(ListUserEntity list);

}
