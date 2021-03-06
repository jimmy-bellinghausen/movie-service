package com.galvanize.controllers;

import com.galvanize.entities.Movie;
import com.galvanize.entities.StarRating;
import com.galvanize.services.MovieService;
import org.springframework.http.ResponseEntity;
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
    public List<Movie> getAllMoviesByTitle(
            @RequestParam String containing,
            @RequestParam(required = false) String actor,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String genre
    ){
        if(actor!=null||director!=null||genre!=null){
            return service.getAllMoviesByTitleAndOptionals(containing, actor, director, Movie.GENRE.valueOf(genre));
        }
        return service.getAllMoviesByTitle(containing);
    }

    @DeleteMapping("/{movieId}")
    public boolean deleteMovieById(@PathVariable long movieId) {return service.deleteMovieById(movieId);}

    @GetMapping("/exists/{imdbId}")
    public ResponseEntity<Boolean> movieExistsByImdbId(@PathVariable String imdbId) { return ResponseEntity.ok(service.movieExistsByImdbId(imdbId)); }

}
