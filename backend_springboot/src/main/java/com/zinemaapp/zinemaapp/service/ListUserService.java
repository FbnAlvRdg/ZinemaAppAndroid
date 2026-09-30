package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import com.zinemaapp.zinemaapp.infrastructure.repository.ListUserRepository;
import com.zinemaapp.zinemaapp.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListUserService {

    private final ListUserRepository listUserRepository;
    private final UserRepository userRepository;

    public ListUserService(ListUserRepository listUserRepository, UserRepository userRepository) {
        this.listUserRepository = listUserRepository;
        this.userRepository = userRepository;
    }

    public ListUserEntity createList(String name, UserEntity userEntity) {
        ListUserEntity list = new ListUserEntity();
        list.setName(name);
        list.setUser(userEntity);

        return listUserRepository.save(list);
    }

    public List<ListUserEntity> getListsByUser(UserEntity userEntity) {
        return listUserRepository.findByUser(userEntity);
    }

    public boolean deleteList(String email, Long listId) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity userEntity = userOptional.get();

        Optional<ListUserEntity> listUserOptional = listUserRepository.findById(listId);

        if (listUserOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUserEntity listUserEntity = listUserOptional.get();

        if (!listUserEntity.getUser().getId().equals(userEntity.getId())) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        listUserRepository.delete(listUserEntity);
        return true;
    }
}
