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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

}