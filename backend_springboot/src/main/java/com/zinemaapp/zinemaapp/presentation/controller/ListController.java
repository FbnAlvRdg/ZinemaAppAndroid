package com.zinemaapp.zinemaapp.presentation.controller;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import com.zinemaapp.zinemaapp.dto.internal.items.AddItemRequest;
import com.zinemaapp.zinemaapp.dto.internal.items.ListItemResponseDTO;
import com.zinemaapp.zinemaapp.dto.internal.lists.CreateListRequest;
import com.zinemaapp.zinemaapp.dto.internal.lists.ListResponseDTO;
import com.zinemaapp.zinemaapp.infrastructure.repository.UserRepository;
import com.zinemaapp.zinemaapp.service.ListItemService;
import com.zinemaapp.zinemaapp.service.ListUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class ListController {
    private final ListUserService listUserService;
    private final UserRepository userRepository;
    private final ListItemService listItemService;

    public ListController(ListUserService listUserService, UserRepository userRepository, ListItemService listItemService) {
        this.listUserService = listUserService;
        this.userRepository = userRepository;
        this.listItemService = listItemService;
    }

    @PostMapping
    public ResponseEntity<ListResponseDTO> createList(
            @RequestBody CreateListRequest request,
            Principal principal) {

        String email = principal.getName();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity userEntity = userOptional.get();

        ListUserEntity list = listUserService.createList(request.getName(), userEntity);
        ListResponseDTO response = new ListResponseDTO(
                list.getId(),
                list.getName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ListResponseDTO>> getLists(Principal principal) {
        String email = principal.getName();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity userEntity = userOptional.get();
        List<ListUserEntity> lists = listUserService.getListsByUser(userEntity);
        List<ListResponseDTO> response = new ArrayList<>();
        for (ListUserEntity list : lists) {
            ListResponseDTO responseDTO = new ListResponseDTO(
                    list.getId(),
                    list.getName()
            );
            response.add(responseDTO);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<Boolean> addItem(
            @PathVariable Long listId,
            @RequestBody AddItemRequest request,
            Principal principal
    ) {
        listItemService.addItem(principal.getName(), listId, request.getTmdbId(), request.getType());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{listId}/items")
    public ResponseEntity<List<ListItemResponseDTO>> getItems(@PathVariable Long listId, Principal principal) {
        return ResponseEntity.ok(listItemService.getItems(principal.getName(), listId));
    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable Long listId, @PathVariable Long itemId, Principal principal) {
        return ResponseEntity.ok(listItemService.deleteItem(principal.getName(), listId, itemId));
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<Boolean> deleteList(@PathVariable Long listId, Principal principal) {
        return ResponseEntity.ok(listUserService.deleteList(principal.getName(), listId));
    }
}
