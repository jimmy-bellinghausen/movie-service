package com.galvanize.services;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    public void getAllMovies(){
        Movie expected = new Movie();
        expected.setMovieId(1L);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(expected);
        when(repository.findAll()).thenReturn(expectedMovies);
        assertEquals(expectedMovies, service.getAllMovies());
    }

    @Test
    public void getMovieByImdbId(){
        Movie expected = new Movie();
        expected.setMovieId(1L);
        expected.setImdbId("1");
        when(repository.findByImdbId(anyString())).thenReturn(Optional.of(expected));
        assertEquals(expected, service.getMovieByImdbId(expected.getImdbId()));
    }

    @Test
    public void getAllMoviesByTitle(){
        Movie expected = new Movie();
        expected.setTitle("A Very Unique Title");
        expected.setMovieId(1L);
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(expected);
        when(repository.findAllByTitleContaining(anyString())).thenReturn(expectedList);
        assertEquals(expectedList, service.getAllMoviesByTitle("Who cares."));
    }

}