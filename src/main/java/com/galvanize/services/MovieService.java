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

    public List<Movie> getAllMoviesByTitleAndOptionals(String title, String actor, String director, Movie.GENRE genre) {
        if(actor==null){ actor=""; }
        if(director==null){ director=""; }
        if(genre==null){
            return repository.findAllByTitleContainingAndActorsContainingAndDirectorContaining(title, actor, director);
        }
        return repository.findAllByTitleContainingAndActorsContainingAndDirectorContainingAndGenre(title, actor, director, genre);
    }

    public boolean deleteMovieById(long movieId) {
        return repository.deleteByMovieId(movieId)==1;
    }

    public boolean movieExistsByImdbId(String imdbId) {
        return repository.existsByImdbId(imdbId);
    }
}
