package com.zinemaapp.zinemaapp.application.usecase.lists;

import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateListUseCase {
    private final ListUserRepository listUserRepository;

    public CreateListUseCase(ListUserRepository listUserRepository) {
        this.listUserRepository = listUserRepository;
    }

    public ListUser createList(String name, Long userId) {
        return listUserRepository.save(name, userId);
    }
}
