package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListItemEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.ListUserEntity;
import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.film.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.tvserie.TmdbTvSerieResponse;
import com.zinemaapp.zinemaapp.presentation.dto.items.ListItemResponseDTO;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.client.TmdbClient;
import com.zinemaapp.zinemaapp.infrastructure.repository.ListItemRepository;
import com.zinemaapp.zinemaapp.infrastructure.repository.ListUserRepository;
import com.zinemaapp.zinemaapp.infrastructure.repository.UserRepository;
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

    public ListItemEntity addItem(String email, Long listId, Long tmdbId, String type) {

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity userEntity = userOptional.get();

        Optional<ListUserEntity> listUserOptional = listUserRepository.findById(listId);

        if (listUserOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUserEntity listUserEntity = listUserOptional.get();

        if (!listUserEntity.getUser().getId().equals(userEntity.getId())) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        if (listItemRepository.existsByListAndTmdbId(listUserEntity, tmdbId)) {
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

        ListItemEntity listItemEntity = new ListItemEntity(tmdbId, type, title, poster, listUserEntity);
        return listItemRepository.save(listItemEntity);

    }

    public List<ListItemResponseDTO> getItems(String email, Long listId) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity userEntity = userOptional.get();

        Optional<ListUserEntity> listUserOptional = listUserRepository.findById(listId);

        if (listUserOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUserEntity listUserEntity = listUserOptional.get();

        if (!listUserEntity.getUser().getId().equals(userEntity.getId())) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        List<ListItemEntity> items = listItemRepository.findByList(listUserEntity);
        List<ListItemResponseDTO> response = new ArrayList<>();
        for (ListItemEntity item : items) {
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
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        UserEntity userEntity = userOptional.get();

        Optional<ListUserEntity> listUserOptional = listUserRepository.findById(listId);

        if (listUserOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la lista");
        }

        ListUserEntity listUserEntity = listUserOptional.get();

        if (!listUserEntity.getUser().getId().equals(userEntity.getId())) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        Optional<ListItemEntity> itemOptional = listItemRepository.findById(itemId);

        if (itemOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el item");
        }

        ListItemEntity item = itemOptional.get();

        if (item.getList().getId() != (listUserEntity.getId())) {
            throw new RuntimeException("El item no pertenece a la lista");
        }

        listItemRepository.delete(item);
        return true;
    }
}
