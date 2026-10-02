package com.zinemaapp.zinemaapp.application.usecase.lists.listItem;

import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListItemRepository;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListUserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemUseCase {
    private final ListItemRepository listItemRepository;
    private final ListUserRepository listUserRepository;

    public DeleteItemUseCase(ListItemRepository listItemRepository, ListUserRepository listUserRepository) {
        this.listItemRepository = listItemRepository;
        this.listUserRepository = listUserRepository;
    }

    public boolean deleteItem(Long userId, Long listId, Long itemId) {

        ListUser listUser = listUserRepository.findById(listId);
        if (!listUser.getUser().getId().equals(userId)) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        ListItem listItem = listItemRepository.findById(itemId);
        Long listItemId = (long) listItem.getList().getId();
        if (!listItemId.equals(listId)) {
            throw new RuntimeException("El item no pertenece a la lista");
        }

        listItemRepository.delete(listItem);
        return true;
    }
}
