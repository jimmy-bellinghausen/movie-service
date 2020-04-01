package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Integer deleteByMovieId(long movieId);
    Optional<Movie> findByImdbId(String imdbId);
}
