package com.zinemaapp.zinemaapp.controller;

import com.zinemaapp.zinemaapp.domain.ListUser;
import com.zinemaapp.zinemaapp.domain.User;
import com.zinemaapp.zinemaapp.dto.internal.items.AddItemRequest;
import com.zinemaapp.zinemaapp.dto.internal.lists.CreateListRequest;
import com.zinemaapp.zinemaapp.dto.internal.lists.ListResponseDTO;
import com.zinemaapp.zinemaapp.repository.UserRepository;
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
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        User user = userOptional.get();

        ListUser list = listUserService.createList(request.getName(), user);
        ListResponseDTO response = new ListResponseDTO(
                list.getId(),
                list.getName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ListResponseDTO>> getLists(Principal principal) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        User user = userOptional.get();
        List<ListUser> lists = listUserService.getListsByUser(user);
        List<ListResponseDTO> response = new ArrayList<>();
        for (ListUser list : lists) {
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
}
