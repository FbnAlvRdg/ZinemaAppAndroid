package com.zinemaapp.zinemaapp.application.usecase.lists;

import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListsByUserUseCase {
    private final ListUserRepository listUserRepository;

    public GetListsByUserUseCase(ListUserRepository listUserRepository) {
        this.listUserRepository = listUserRepository;
    }

    public List<ListUser> getListsByUser(Long userId) {
        return listUserRepository.findByUser(userId);
    }
}
