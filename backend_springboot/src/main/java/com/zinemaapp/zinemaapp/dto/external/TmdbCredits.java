package com.zinemaapp.zinemaapp.dto.external;

import java.util.List;

public class TmdbCredits {
    private List<TmdbCrew> crew;
    private List<TmdbCast> cast;

    public List<TmdbCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<TmdbCrew> crew) {
        this.crew = crew;
    }

    public List<TmdbCast> getCast() {
        return cast;
    }

    public void setCast(List<TmdbCast> cast) {
        this.cast = cast;
    }
}
