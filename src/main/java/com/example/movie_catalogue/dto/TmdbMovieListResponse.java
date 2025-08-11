package com.example.movie_catalogue.dto;

import java.util.List;

public class TmdbMovieListResponse {
    private int page;
    private List<TmdbMovieDto> results;

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public List<TmdbMovieDto> getResults() { return results; }
    public void setResults(List<TmdbMovieDto> results) { this.results = results; }
}
