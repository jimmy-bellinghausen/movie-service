package com.galvanize.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    long movieId;
    @Column(unique = true)
    String imdbId;
    @Column
    String actors;
    @Column
    String director;
    @Column
    String title;
    @Column
    LocalDate released;
    @Column
    String year;

    public Movie() {
    }

    public Movie(long movieId, String imdbId, String actors, String director, String title, LocalDate released) {
        this.movieId = movieId;
        this.imdbId = imdbId;
        this.actors = actors;
        this.director = director;
        this.title = title;
        this.released = released;
    }

    public Movie(String imdbId, String actors, String director, String title, LocalDate released, String year) {
        this.imdbId = imdbId;
        this.actors = actors;
        this.director = director;
        this.title = title;
        this.released = released;
        this.year = year;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear(){
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getMovieId() == movie.getMovieId() &&
                Objects.equals(getImdbId(), movie.getImdbId()) &&
                Objects.equals(getActors(), movie.getActors()) &&
                Objects.equals(getDirector(), movie.getDirector()) &&
                Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getReleased(), movie.getReleased());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getImdbId(), getActors(), getDirector(), getTitle(), getReleased());
    }
}
