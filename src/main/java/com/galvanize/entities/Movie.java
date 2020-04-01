package com.galvanize.entities;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Movie(String imdbId, String actors, String director, String title, LocalDate released) {
        this.imdbId = imdbId;
        this.actors = actors;
        this.director = director;
        this.title = title;
        this.released = released;
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

    public String getYear(){
        return ""+released.getYear();
    }
}
