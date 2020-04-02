package com.galvanize.services;

import com.galvanize.entities.Movie;
import com.galvanize.entities.StarRating;
import com.galvanize.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MovieService {
    MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie postMovie(Movie movieToPost) {
        return repository.save(movieToPost);
    }

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Movie getMovieByImdbId(String imdbId) {
        return repository.findByImdbId(imdbId).orElse(null);
    }

    public List<Movie> getAllMoviesByTitle(String searchPhrase) {
        return repository.findAllByTitleContaining(searchPhrase);
    }

    public Movie patchStarRating(long movieId, StarRating ratingToPatch) {
        Movie foundMovie = repository.findById(movieId).orElse(null);
        if(foundMovie!=null){
            foundMovie.getRatings().put(ratingToPatch.getUserId(), ratingToPatch);
            return repository.save(foundMovie);
        }
        return null;
    }
}
