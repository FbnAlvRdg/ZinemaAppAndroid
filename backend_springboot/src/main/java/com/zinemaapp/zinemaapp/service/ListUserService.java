package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.domain.ListUser;
import com.zinemaapp.zinemaapp.domain.User;
import com.zinemaapp.zinemaapp.repository.ListUserRepository;
import com.zinemaapp.zinemaapp.repository.UserRepository;
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

    public ListUser createList(String name, User user) {
        ListUser list = new ListUser();
        list.setName(name);
        list.setUser(user);

        return listUserRepository.save(list);
    }

    public List<ListUser> getListsByUser(User user) {
        return listUserRepository.findByUser(user);
    }

    public boolean deleteList(String email, Long listId) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        User user = userOptional.get();

        Optional<ListUser> listUserOptional = listUserRepository.findById(listId);

        if (listUserOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUser listUser = listUserOptional.get();

        if (!listUser.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        listUserRepository.delete(listUser);
        return true;
    }
}
