package com.zinemaapp.zinemaapp.infrastructure.repository;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListItemRepository extends JpaRepository<ListItemEntity, Long> {
    boolean existsByListAndTmdbId(ListUserEntity list, Long tmdbId);
     List<ListItemEntity> findByList(ListUserEntity listUserEntity);
}
