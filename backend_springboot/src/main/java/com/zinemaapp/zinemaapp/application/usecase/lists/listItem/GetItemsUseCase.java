package com.zinemaapp.zinemaapp.application.usecase.lists.listItem;

import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListItemRepository;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetItemsUseCase {
    private final ListUserRepository listUserRepository;
    private final ListItemRepository listItemRepository;

    public GetItemsUseCase(ListUserRepository listUserRepository, ListItemRepository listItemRepository) {
        this.listUserRepository = listUserRepository;
        this.listItemRepository = listItemRepository;
    }

    public List<ListItem> getItems(Long userId, Long listId) {
        ListUser listUser = listUserRepository.findById(listId);

        if (!listUser.getUser().getId().equals(userId)){
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        return listItemRepository.findByList(listId);
    }
}
