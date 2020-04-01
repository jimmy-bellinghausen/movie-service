package com.galvanize.controllers;

import com.galvanize.services.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }
}
