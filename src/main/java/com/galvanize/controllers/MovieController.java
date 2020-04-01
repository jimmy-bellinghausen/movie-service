package com.galvanize.controllers;

import com.galvanize.entities.Movie;
import com.galvanize.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    public Movie postMovie(@RequestBody Movie input){
        return service.postMovie(input);
    }

    @GetMapping
    public List<Movie> getAllMovies(){
        return service.getAllMovies();
    }

    @GetMapping("/{imdbId}")
    public Movie getMovieByImdbId(@PathVariable String imdbId){
        return service.getMovieByImdbId(imdbId);
    }

    @GetMapping("/title")
    public List<Movie> getAllMoviesByTitle(@RequestParam String containing){ return service.getAllMoviesByTitle(containing); }

}
