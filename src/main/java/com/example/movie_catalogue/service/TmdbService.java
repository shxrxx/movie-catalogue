package com.example.movie_catalogue.service;

import com.example.movie_catalogue.dto.TmdbMovieDto;
import com.example.movie_catalogue.dto.TmdbMovieListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class TmdbService {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String imageBaseUrl;
    private final String apiBase = "https://api.themoviedb.org/3";

    public TmdbService(RestTemplate restTemplate,
                       @Value("${tmdb.api.key}") String apiKey,
                       @Value("${tmdb.image.base-url}") String imageBaseUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.imageBaseUrl = imageBaseUrl;
    }

    public List<TmdbMovieDto> getTrendingMovies() {
        String url = apiBase + "/trending/movie/day?api_key=" + apiKey;
        TmdbMovieListResponse resp = restTemplate.getForObject(url, TmdbMovieListResponse.class);
        return resp != null && resp.getResults() != null ? resp.getResults() : Collections.emptyList();
    }

    public List<TmdbMovieDto> searchMovies(String query) {
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        String url = apiBase + "/search/movie?api_key=" + apiKey + "&query=" + query.replace(" ", "%20");
        TmdbMovieListResponse resp = restTemplate.getForObject(url, TmdbMovieListResponse.class);
        return resp != null && resp.getResults() != null ? resp.getResults() : Collections.emptyList();
    }

    public TmdbMovieDto getMovieDetails(Long tmdbId) {
        String url = apiBase + "/movie/" + tmdbId + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, TmdbMovieDto.class);
    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }
}
