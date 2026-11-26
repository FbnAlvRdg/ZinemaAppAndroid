package com.zinemaapp.zinemaapp.infrastructure.persistence.repository.lists.listItem;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ListItemJpaRepository extends JpaRepository<ListItemEntity, Long> {
    boolean existsByListAndTmdbId(ListUserEntity listUserEntity, Long tmdbId);

    List<ListItemEntity> findByList(ListUserEntity list);

    @Modifying
    @Transactional
    @Query("DELETE FROM ListItemEntity item WHERE item.id = :id")
    int deleteItemById(@Param("id") Long id);
}
