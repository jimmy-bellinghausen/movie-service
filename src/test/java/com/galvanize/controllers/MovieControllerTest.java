package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Movie;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        when(service.getMovieByImdbId(anyString())).thenReturn(expected);
        mvc.perform(get("/api/movie/"+expected.getImdbId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expected));
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

}