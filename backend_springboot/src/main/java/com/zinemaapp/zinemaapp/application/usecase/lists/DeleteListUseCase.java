package com.zinemaapp.zinemaapp.application.usecase.lists;

import com.zinemaapp.zinemaapp.domain.repository.lists.ListUserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteListUseCase {
    private final ListUserRepository listUserRepository;

    public DeleteListUseCase(ListUserRepository listUserRepository) {
        this.listUserRepository = listUserRepository;
    }

    public boolean deleteList(Long userId, Long listId){
        return listUserRepository.delete(userId, listId);
    }
}
