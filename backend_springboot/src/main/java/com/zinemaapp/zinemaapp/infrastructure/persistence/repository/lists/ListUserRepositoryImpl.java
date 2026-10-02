package com.zinemaapp.zinemaapp.infrastructure.persistence.repository.lists;

import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListUserRepository;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.mapper.ListUserEntityMapper;
import com.zinemaapp.zinemaapp.infrastructure.persistence.repository.user.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ListUserRepositoryImpl implements ListUserRepository {
    private final ListUserJpaRepository listUserJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ListUserEntityMapper listUserEntityMapper;

    public ListUserRepositoryImpl(ListUserJpaRepository listUserJpaRepository, UserJpaRepository userJpaRepository, ListUserEntityMapper listUserEntityMapper) {
        this.listUserJpaRepository = listUserJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.listUserEntityMapper = listUserEntityMapper;
    }

    @Override
    public List<ListUser> findByUser(Long userId) {

        Optional<UserEntity> userOptional =
                userJpaRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity user = userOptional.get();

        return listUserJpaRepository.findByUserEntity(user)
                .stream()
                .map(listUserEntityMapper::toDomain)
                .toList();
    }

    @Override
    public ListUser findById(Long listId) {

        Optional<ListUserEntity> listUserOptional = listUserJpaRepository.findById(listId);

        if (listUserOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        return listUserEntityMapper.toDomain(listUserOptional.get());
    }

    @Override
    public ListUser save(String name, Long userId) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(userId);

        if (userEntity.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity user = userEntity.get();

        ListUserEntity listUserEntity = new ListUserEntity();
        listUserEntity.setName(name);
        listUserEntity.setUser(user);

        ListUserEntity savedList = listUserJpaRepository.save(listUserEntity);
        return listUserEntityMapper.toDomain(savedList);
    }

    @Override
    public boolean delete(Long userId, Long listId) {
        Optional<UserEntity> userOptional = userJpaRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado al usuario");
        }

        UserEntity userEntity = userOptional.get();

        Optional<ListUserEntity> listUserOptional = listUserJpaRepository.findById(listId);

        if (listUserOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUserEntity listUserEntity = listUserOptional.get();

        if (!listUserEntity.getUser().getId().equals(userEntity.getId())) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        listUserJpaRepository.delete(listUserEntity);
        return true;
    }

}
