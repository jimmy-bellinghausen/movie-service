package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Integer deleteByMovieId(long movieId);
    Optional<Movie> findByImdbId(String imdbId);
    List<Movie> findAllByTitleContaining(String title);
    List<Movie> findAllByTitleContainingAndActorsContainingAndDirectorContaining(String title, String actor, String director);
    List<Movie> findAllByTitleContainingAndActorsContainingAndDirectorContainingAndGenre(String title, String actor, String director, Movie.GENRE genre);
    boolean existsByImdbId(String imdbId);
}
