package com.example.movie_catalogue.controller;

import com.example.movie_catalogue.dto.TmdbMovieDto;
import com.example.movie_catalogue.model.FavoriteMovie;
import com.example.movie_catalogue.service.FavoriteService;
import com.example.movie_catalogue.service.TmdbService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    private final TmdbService tmdbService;
    private final FavoriteService favoriteService;

    public MovieController(TmdbService tmdbService, FavoriteService favoriteService) {
        this.tmdbService = tmdbService;
        this.favoriteService = favoriteService;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "q", required = false) String q, Model model) {
        List<TmdbMovieDto> movies;
        if (q != null && !q.isEmpty()) {
            movies = tmdbService.searchMovies(q);
            model.addAttribute("q", q);
        } else {
            movies = tmdbService.getTrendingMovies();
        }

        List<FavoriteMovie> favs = favoriteService.listFavorites();
        Set<Long> favIds = favs.stream().map(FavoriteMovie::getTmdbId).collect(Collectors.toSet());

        model.addAttribute("movies", movies);
        model.addAttribute("favoriteIds", favIds);
        model.addAttribute("imageBaseUrl", tmdbService.getImageBaseUrl());
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable("id") Long id, Model model) {
        TmdbMovieDto movie = tmdbService.getMovieDetails(id);
        boolean isFavorite = favoriteService.isFavorite(id);
        model.addAttribute("movie", movie);
        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("imageBaseUrl", tmdbService.getImageBaseUrl());
        return "movie";
    }

    @GetMapping("/favorites")
    public String favoritesPage(Model model) {
        List<FavoriteMovie> favs = favoriteService.listFavorites();
        model.addAttribute("favorites", favs);
        model.addAttribute("imageBaseUrl", tmdbService.getImageBaseUrl());
        return "favorites";
    }

    @PostMapping("/favorites/add")
    public String addFavorite(@RequestParam("tmdbId") Long tmdbId, HttpServletRequest request) {
        try {
            TmdbMovieDto dto = tmdbService.getMovieDetails(tmdbId);
            favoriteService.addFavoriteFromTmdb(dto);
        } catch (Exception e) {
            // simple error handling; in production add logging and user feedback
            e.printStackTrace();
        }
        String referer = request.getHeader("referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

    @PostMapping("/favorites/remove")
    public String removeFavorite(@RequestParam("tmdbId") Long tmdbId, HttpServletRequest request) {
        favoriteService.removeFavorite(tmdbId);
        String referer = request.getHeader("referer");
        return "redirect:" + (referer != null ? referer : "/favorites");
    }
}
