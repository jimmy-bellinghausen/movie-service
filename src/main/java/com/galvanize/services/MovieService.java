package com.galvanize.services;

import com.galvanize.entities.Movie;
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
}
