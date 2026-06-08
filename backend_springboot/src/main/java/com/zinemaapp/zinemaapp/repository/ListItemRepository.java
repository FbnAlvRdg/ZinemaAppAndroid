package com.zinemaapp.zinemaapp.repository;

import com.zinemaapp.zinemaapp.domain.ListItem;
import com.zinemaapp.zinemaapp.domain.ListUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    public boolean existsByListAndTmdbId(ListUser list, Long tmdbId);
}
