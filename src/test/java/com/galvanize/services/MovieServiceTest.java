package com.galvanize.services;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class MovieServiceTest {
    @Autowired
    MovieService service;

    @MockBean
    MovieRepository repository;

    @Test
    public void postMovie(){
        Movie movieToPost = new Movie();
        Movie expected = new Movie();
        expected.setMovieId(1L);
        when(repository.save(any(Movie.class))).thenReturn(expected);
        assertEquals(expected, service.postMovie(movieToPost));
    }

}