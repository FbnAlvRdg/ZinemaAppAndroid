package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.domain.ListUser;
import com.zinemaapp.zinemaapp.domain.User;
import com.zinemaapp.zinemaapp.repository.ListUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUserService {

    private final ListUserRepository listUserRepository;

    public ListUserService(ListUserRepository listUserRepository) {
        this.listUserRepository = listUserRepository;
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
}
