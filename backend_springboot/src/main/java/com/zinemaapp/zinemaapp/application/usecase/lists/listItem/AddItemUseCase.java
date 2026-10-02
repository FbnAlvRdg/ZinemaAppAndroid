package com.zinemaapp.zinemaapp.application.usecase.lists.listItem;

import com.zinemaapp.zinemaapp.domain.model.film.Film;
import com.zinemaapp.zinemaapp.domain.model.lists.ListItem;
import com.zinemaapp.zinemaapp.domain.model.lists.ListUser;
import com.zinemaapp.zinemaapp.domain.model.tvserie.TvSerie;
import com.zinemaapp.zinemaapp.domain.repository.film.FilmRepository;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListItemRepository;
import com.zinemaapp.zinemaapp.domain.repository.lists.ListUserRepository;
import com.zinemaapp.zinemaapp.domain.repository.tvseries.TvSerieRepository;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.film.TmdbFilmResponse;
import com.zinemaapp.zinemaapp.infrastructure.tmdb.dto.tvserie.TmdbTvSerieResponse;
import org.springframework.stereotype.Service;

@Service
public class AddItemUseCase {
    private final ListUserRepository listUserRepository;
    private final ListItemRepository listItemRepository;
    private final FilmRepository filmRepository;
    private final TvSerieRepository tvSerieRepository;
    private static final String TYPE_MOVIE = "movie";
    private static final String TYPE_TV = "tv";

    public AddItemUseCase(ListUserRepository listUserRepository, ListItemRepository listItemRepository, FilmRepository filmRepository, TvSerieRepository tvSerieRepository) {
        this.listUserRepository = listUserRepository;
        this.listItemRepository = listItemRepository;
        this.filmRepository = filmRepository;
        this.tvSerieRepository = tvSerieRepository;
    }

    public ListItem addItem(Long userId, Long listId, Long tmdbId, String type) {
        ListUser listUser = listUserRepository.findById(listId);

        if (!listUser.getUser().getId().equals(userId)) {
            throw new RuntimeException("El usuario no tiene permisos sobre la lista");
        }

        if (listItemRepository.existsByListAndTmdbId(listId, tmdbId)) {
            throw new RuntimeException("El item ya se encuentra en la lista");
        }

        String title;
        String poster;

        if (TYPE_MOVIE.equals(type)) {
            Film film = filmRepository.findById(tmdbId.intValue());
            title = film.getTitle();
            poster = film.getPoster();

        } else if (TYPE_TV.equals(type)) {

            TvSerie tvSerie = tvSerieRepository.findById(tmdbId.intValue());

            title = tvSerie.getName();
            poster = tvSerie.getPoster();

        } else {
            throw new RuntimeException("Tipo no válido: " + type);
        }

        ListItem listItem = new ListItem(tmdbId, type, title, poster, listUser);
        listItemRepository.save(listItem);
        return listItem;
    }
}
