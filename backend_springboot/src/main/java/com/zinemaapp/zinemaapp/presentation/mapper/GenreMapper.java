package com.zinemaapp.zinemaapp.presentation.mapper;

import com.zinemaapp.zinemaapp.domain.model.common.Genre;
import com.zinemaapp.zinemaapp.presentation.dto.credits.GenreDTO;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    public GenreDTO toDTO(Genre genre){
        return new GenreDTO(
                genre.getId(),
                genre.getName()
        );
    }
}
