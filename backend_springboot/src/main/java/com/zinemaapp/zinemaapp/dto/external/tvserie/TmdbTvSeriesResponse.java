package com.zinemaapp.zinemaapp.dto.external.tvserie;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zinemaapp.zinemaapp.dto.external.film.TmdbFilmResponse;

import java.util.List;

public class TmdbTvSeriesResponse {
    @JsonProperty("page")
    private int pages;
    @JsonProperty("results")
    private List<TmdbTvSerieResponse> results;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_results")
    private int totalResults;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<TmdbTvSerieResponse> getResults() {
        return results;
    }

    public void setResults(List<TmdbTvSerieResponse> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
