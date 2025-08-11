package com.example.movie_catalogue.repository;

import com.example.movie_catalogue.model.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
    Optional<FavoriteMovie> findByTmdbId(Long tmdbId);
    void deleteByTmdbId(Long tmdbId);
}
