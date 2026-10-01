package com.zinemaapp.zinemaapp.presentation.mapper;

import com.zinemaapp.zinemaapp.domain.model.Actor;
import com.zinemaapp.zinemaapp.presentation.dto.credits.ActorDTO;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {
    public ActorDTO toDTO(Actor actor) {
        return new ActorDTO(
                actor.getId(),
                actor.getName(),
                actor.getCharacter()
        );
    }
}
