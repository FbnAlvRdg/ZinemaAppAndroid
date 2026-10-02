package com.zinemaapp.zinemaapp.infrastructure.persistence.repository.lists.listItem;

import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListItemRepository;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.mapper.ListItemEntityMapper;
import com.zinemaapp.zinemaapp.infrastructure.persistence.repository.lists.ListUserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ListItemRepositoryImpl implements ListItemRepository {
    private final ListItemJpaRepository listItemJpaRepository;
    private final ListUserJpaRepository listUserJpaRepository;
    private final ListItemEntityMapper listItemEntityMapper;

    public ListItemRepositoryImpl(ListItemJpaRepository listItemJpaRepository, ListUserJpaRepository listUserJpaRepository, ListItemEntityMapper listItemEntityMapper) {
        this.listItemJpaRepository = listItemJpaRepository;
        this.listUserJpaRepository = listUserJpaRepository;
        this.listItemEntityMapper = listItemEntityMapper;
    }

    @Override
    public boolean existsByListAndTmdbId(Long listId, Long tmdbId) {
        Optional<ListUserEntity> listUserEntityOptional = listUserJpaRepository.findById(listId);

        if (listUserEntityOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUserEntity listUserEntity = listUserEntityOptional.get();

        return listItemJpaRepository.existsByListAndTmdbId(listUserEntity, tmdbId);
    }

    @Override
    public ListItem save(ListItem listItem) {
        ListItemEntity listItemEntity = listItemEntityMapper.toEntity(listItem);
        ListItemEntity savedItem = listItemJpaRepository.save(listItemEntity);

        return listItemEntityMapper.toDomain(savedItem);
    }

    @Override
    public List<ListItem> findByList(Long listId) {
        Optional<ListUserEntity> listUserEntityOptional = listUserJpaRepository.findById(listId);

        if (listUserEntityOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUserEntity listUserEntity = listUserEntityOptional.get();

        List<ListItemEntity> listItemEntityList = listItemJpaRepository.findByList(listUserEntity);

        return listItemEntityList.
                stream()
                .map(listItemEntityMapper::toDomain)
                .toList();
    }

    @Override
    public ListItem findById(Long itemId) {
        Optional<ListItemEntity> listItemEntityOptional = listItemJpaRepository.findById(itemId);

        if (listItemEntityOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el item");
        }

        ListItemEntity listItemEntity = listItemEntityOptional.get();

        return listItemEntityMapper.toDomain(listItemEntity);
    }

    @Override
    public boolean delete(ListItem listItem) {
        ListItemEntity listItemEntity = listItemEntityMapper.toEntity(listItem);
        listItemJpaRepository.delete(listItemEntity);
        return true;
    }
}

