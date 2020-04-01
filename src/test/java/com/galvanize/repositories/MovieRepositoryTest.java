package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MovieRepositoryTest {

    @Autowired
    MovieRepository repository;

    @Test
    public void findByImdbId(){
        Movie movieToFind = repository.save(new Movie("24", "Johnny Depp, Someone Else", "That guy", "The Movie", LocalDate.now(), "2020"));
        assertEquals(Optional.of(movieToFind), repository.findByImdbId(movieToFind.getImdbId()));
    }

    @Test
    public void deleteMovie(){
        Movie movieToDelete = repository.save(new Movie("24", "Johnny Depp, Someone Else", "That guy", "The Movie", LocalDate.now(), "2020"));
        assertEquals(1,repository.deleteByMovieId(movieToDelete.getMovieId()));
    }

    @Test
    public void failToDeleteMovie(){
        assertEquals(0,repository.deleteByMovieId(1L));
    }
}