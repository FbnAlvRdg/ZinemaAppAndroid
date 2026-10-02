package com.zinemaapp.zinemaapp.presentation.controller;

import com.zinemaapp.zinemaapp.application.usecase.lists.CreateListUseCase;
import com.zinemaapp.zinemaapp.application.usecase.lists.DeleteListUseCase;
import com.zinemaapp.zinemaapp.application.usecase.lists.GetListsByUserUseCase;
import com.zinemaapp.zinemaapp.application.usecase.lists.listItem.AddItemUseCase;
import com.zinemaapp.zinemaapp.application.usecase.lists.listItem.DeleteItemUseCase;
import com.zinemaapp.zinemaapp.application.usecase.lists.listItem.GetItemsUseCase;
import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.presentation.dto.lists.items.AddItemRequest;
import com.zinemaapp.zinemaapp.presentation.dto.lists.items.ListItemResponseDTO;
import com.zinemaapp.zinemaapp.presentation.dto.lists.CreateListRequest;
import com.zinemaapp.zinemaapp.presentation.dto.lists.ListResponseDTO;
import com.zinemaapp.zinemaapp.domain.repository.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class ListController {
    private final CreateListUseCase createListUseCase;
    private final GetListsByUserUseCase getListsByUserUseCase;
    private final DeleteListUseCase deleteListUseCase;
    private final AddItemUseCase addItemUseCase;
    private final GetItemsUseCase getItemsUseCase;
    private final DeleteItemUseCase deleteItemUseCase;
    private final UserRepository userRepository;

    public ListController(CreateListUseCase createListUseCase, GetListsByUserUseCase getListsByUserUseCase, DeleteListUseCase deleteListUseCase, AddItemUseCase addItemUseCase, GetItemsUseCase getItemsUseCase, DeleteItemUseCase deleteItemUseCase, UserRepository userRepository) {
        this.createListUseCase = createListUseCase;
        this.getListsByUserUseCase = getListsByUserUseCase;
        this.deleteListUseCase = deleteListUseCase;
        this.addItemUseCase = addItemUseCase;
        this.getItemsUseCase = getItemsUseCase;
        this.deleteItemUseCase = deleteItemUseCase;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ListResponseDTO> createList(
            @RequestBody CreateListRequest request,
            Principal principal) {

        User user = getUserFromPrincipal(principal);

        ListUser listUser = createListUseCase.createList(
                request.getName(),
                user.getId()
        );

        ListResponseDTO response = new ListResponseDTO(
                listUser.getId(),
                listUser.getName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ListResponseDTO>> getListsByUser(Principal principal) {

        User user = getUserFromPrincipal(principal);

        List<ListUser> listsUser = getListsByUserUseCase.getListsByUser(user.getId());
        List<ListResponseDTO> response = new ArrayList<>();

        for (ListUser listUser : listsUser) {
            ListResponseDTO listResponseDTO = new ListResponseDTO(
                    listUser.getId(),
                    listUser.getName()
            );

            response.add(listResponseDTO);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<Boolean> deleteList(
            @PathVariable Long listId,
            Principal principal
    ) {

        User user = getUserFromPrincipal(principal);

        boolean deleted = deleteListUseCase.deleteList(
                user.getId(),
                listId
        );

        return ResponseEntity.ok(deleted);
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<Boolean> addItem(
            @PathVariable Long listId,
            @RequestBody AddItemRequest request,
            Principal principal
    ) {
        User user = getUserFromPrincipal(principal);
        addItemUseCase.addItem(
                user.getId(),
                listId,
                request.getTmdbId(),
                request.getType()
        );

        return ResponseEntity.ok(true);
    }

    @GetMapping("/{listId}/items")
    public ResponseEntity<List<ListItemResponseDTO>> getItems(
            @PathVariable Long listId,
            Principal principal
    ) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        User user = userOptional.get();

        List<ListItem> items = getItemsUseCase.getItems(user.getId(), listId);
        List<ListItemResponseDTO> response = new ArrayList<>();

        for (ListItem item : items) {
            ListItemResponseDTO listItemResponseDTO = new ListItemResponseDTO(
                    item.getId(),
                    item.getTmdbId(),
                    item.getType(),
                    item.getTitle(),
                    item.getPoster()
            );

            response.add(listItemResponseDTO);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable Long listId, @PathVariable Long itemId, Principal principal) {
        User user = getUserFromPrincipal(principal);
        return ResponseEntity.ok(deleteItemUseCase.deleteItem(user.getId(), listId, itemId));
    }

    private User getUserFromPrincipal(Principal principal) {

        String email = principal.getName();

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        return userOptional.get();
    }
}
