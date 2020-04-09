package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Movie;
import com.galvanize.entities.StarRating;
import com.galvanize.services.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MovieControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    MovieService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void postMovie() throws Exception{
        Movie expected = new Movie();
        String json = mapper.writeValueAsString(expected);
        expected.setMovieId(1L);
        when(service.postMovie(any(Movie.class))).thenReturn(expected);
        mvc.perform(post("/api/movie").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expected));
    }

    @Test
    public void getAllMovies() throws Exception{
        Movie expected = new Movie();
        expected.setMovieId(1L);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(expected);
        when(service.getAllMovies()).thenReturn(expectedMovies);
        mvc.perform(get("/api/movie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(expected));
    }

    @Test
    public void getMovieByImdbId() throws Exception{
        Movie expected = new Movie();
        expected.setMovieId(1L);
        expected.setImdbId("1");
        when(service.getMovieByImdbId(anyString())).thenReturn(null);
        mvc.perform(get("/api/movie/"+expected.getImdbId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expected))
                .andDo(print());
    }

    @Test
    public void getAllMoviesByTitle() throws Exception{
        Movie expected = new Movie();
        expected.setTitle("A Very Unique Title");
        expected.setMovieId(1L);
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(expected);
        when(service.getAllMoviesByTitle(anyString())).thenReturn(expectedList);
        mvc.perform(get("/api/movie/title?containing=whatever"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(expected));
    }

    @Test
    public void patchStarRating() throws Exception{
        Movie expected = new Movie();
        StarRating expectedStarRating = new StarRating(1L, StarRating.PossibleRatings.FIVE);
        expected.setMovieId(1L);
        Map<Long, StarRating> expectedRatings = new HashMap<>();
        expectedRatings.put(expectedStarRating.getUserId(), expectedStarRating);

        mvc.perform(patch("/api/movie/"+expected.getMovieId()).content(mapper.writeValueAsString(expectedStarRating)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ratings.1.userId").value(expectedStarRating.getUserId()));
    }

    @Test
    public void getAllMoviesByOptionals() throws Exception{
        Movie expected = new Movie();
        expected.setMovieId(1L);
        expected.setActors("Search Actor");
        expected.setDirector("The Director I Look For");
        expected.setGenre(Movie.GENRE.HORROR);
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(expected);
        when(service.getAllMoviesByTitleAndOptionals(anyString(),anyString(),anyString(),any(Movie.GENRE.class))).thenReturn(expectedMovies);

        mvc.perform(get("/api/movie/title?containing=whatever&director=Director&actor=Actor&genre=HORROR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(expected));
    }

    @Test
    public void deleteMovieById() throws Exception{
        when(service.deleteMovieById(anyLong())).thenReturn(true);
        mvc.perform(delete("/api/movie/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void movieExistsByImdbId() throws Exception{
        when(service.movieExistsByImdbId(anyString())).thenReturn(true);
        mvc.perform(get("/api/movie/exists/"+"any imdb id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

}