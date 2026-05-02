package com.zinemaapp.zinemaapp.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TmdbPopularResponse {
    @JsonProperty("pages")
    private int pages;
    @JsonProperty("results")
    private List<TmdbFilmResponse> results;
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

    public List<TmdbFilmResponse> getResults() {
        return results;
    }

    public void setResults(List<TmdbFilmResponse> results) {
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
