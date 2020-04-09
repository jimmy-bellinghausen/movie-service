package com.galvanize.services;

import com.galvanize.entities.Movie;
import com.galvanize.entities.StarRating;
import com.galvanize.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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



    @Test
    public void getAllMoviesByOptionalWithoutGenre(){
        Movie expected = new Movie();
        expected.setMovieId(1L);
        expected.setActors("Search Actor");
        expected.setDirector("The Director I Look For");
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(expected);
        when(repository.findAllByTitleContainingAndActorsContainingAndDirectorContaining(anyString(),anyString(),anyString())).thenReturn(expectedMovies);

        assertEquals(expectedMovies, service.getAllMoviesByTitleAndOptionals("","","", null));
    }

    @Test
    public void getAllMoviesByOptional(){
        Movie expected = new Movie();
        expected.setMovieId(1L);
        expected.setActors("Search Actor");
        expected.setDirector("The Director I Look For");
        expected.setGenre(Movie.GENRE.HORROR);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(expected);
        when(repository.findAllByTitleContainingAndActorsContainingAndDirectorContainingAndGenre(anyString(),anyString(),anyString(),any(Movie.GENRE.class))).thenReturn(expectedMovies);

        assertEquals(expectedMovies, service.getAllMoviesByTitleAndOptionals("","","", Movie.GENRE.valueOf("HORROR")));
    }

    @Test
    public void deleteMovieById(){
        when(repository.deleteByMovieId(anyLong())).thenReturn(1);
        assertTrue(service.deleteMovieById(1L));
    }

    @Test
    public void movieExistsByImdbId(){
        when(repository.existsByImdbId(anyString())).thenReturn(true);
        assertTrue(service.movieExistsByImdbId("anything"));
    }

}