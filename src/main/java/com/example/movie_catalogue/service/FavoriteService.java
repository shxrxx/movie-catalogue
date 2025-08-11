package com.example.movie_catalogue.service;

import com.example.movie_catalogue.dto.TmdbMovieDto;
import com.example.movie_catalogue.model.FavoriteMovie;
import com.example.movie_catalogue.repository.FavoriteMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteMovieRepository repository;

    public FavoriteService(FavoriteMovieRepository repository) {
        this.repository = repository;
    }

    public FavoriteMovie addFavoriteFromTmdb(TmdbMovieDto dto) {
        if (dto == null || dto.getId() == null) return null;
        Optional<FavoriteMovie> existing = repository.findByTmdbId(dto.getId());
        if (existing.isPresent()) return existing.get();

        FavoriteMovie f = new FavoriteMovie();
        f.setTmdbId(dto.getId());
        f.setTitle(dto.getTitle());
        f.setOverview(dto.getOverview());
        f.setPosterPath(dto.getPosterPath());
        f.setReleaseDate(dto.getReleaseDate());
        f.setRating(dto.getVoteAverage());
        return repository.save(f);
    }

    public List<FavoriteMovie> listFavorites() {
        return repository.findAll();
    }

    public void removeFavorite(Long tmdbId) {
        repository.deleteByTmdbId(tmdbId);
    }

    public boolean isFavorite(Long tmdbId) {
        return repository.findByTmdbId(tmdbId).isPresent();
    }

    public List<Long> listFavoriteTmdbIds() {
        return repository.findAll().stream().map(FavoriteMovie::getTmdbId).collect(Collectors.toList());
    }
}
