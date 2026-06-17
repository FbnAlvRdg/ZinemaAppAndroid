package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.domain.ListItem;
import com.zinemaapp.zinemaapp.domain.ListUser;
import com.zinemaapp.zinemaapp.domain.User;
import com.zinemaapp.zinemaapp.dto.external.film.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.dto.external.tvserie.TmdbTvSerieResponse;
import com.zinemaapp.zinemaapp.dto.internal.items.ListItemResponseDTO;
import com.zinemaapp.zinemaapp.infrastructure.TmdbClient;
import com.zinemaapp.zinemaapp.repository.ListItemRepository;
import com.zinemaapp.zinemaapp.repository.ListUserRepository;
import com.zinemaapp.zinemaapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListItemService {
    private final ListItemRepository listItemRepository;
    private final UserRepository userRepository;
    private final ListUserRepository listUserRepository;
    private final TmdbClient tmdbClient;

    private static final String TYPE_MOVIE = "movie";
    private static final String TYPE_TV = "tv";

    public ListItemService(ListItemRepository listItemRepository, UserRepository userRepository, ListUserRepository listUserRepository, TmdbClient tmdbClient) {
        this.listItemRepository = listItemRepository;
        this.userRepository = userRepository;
        this.listUserRepository = listUserRepository;
        this.tmdbClient = tmdbClient;
    }

    public ListItem addItem(String email, Long listId, Long tmdbId, String type) {

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

        if (listItemRepository.existsByListAndTmdbId(listUser, tmdbId)) {
            throw new RuntimeException("El item ya se encuentra en la lista");
        }

        String title;
        String poster;

        if (TYPE_MOVIE.equals(type)) {
            TmdbFilmResponse film = tmdbClient.getFilmById(tmdbId.intValue());
            title = film.getTitle();
            poster = film.getPoster();
        } else if (TYPE_TV.equals(type)) {
            TmdbTvSerieResponse serie = tmdbClient.getSeriesById(tmdbId.intValue());
            title = serie.getName();
            poster = serie.getPoster();
        } else {
            throw new RuntimeException("Tipo no válido: " + type);
        }

        ListItem listItem = new ListItem(tmdbId, type, title, poster, listUser);
        return listItemRepository.save(listItem);

    }

    public List<ListItemResponseDTO> getItems(String email, Long listId) {
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

        List<ListItem> items = listItemRepository.findByList(listUser);
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
        return response;
    }

    public boolean deleteItem(String email, Long listId, Long itemId){
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

        Optional<ListItem> itemOptional = listItemRepository.findById(itemId);

        if (itemOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el item");
        }

        ListItem item = itemOptional.get();

        if (item.getList().getId() != (listUser.getId())) {
            throw new RuntimeException("El item no pertenece a la lista");
        }

        listItemRepository.delete(item);
        return true;
    }
}
