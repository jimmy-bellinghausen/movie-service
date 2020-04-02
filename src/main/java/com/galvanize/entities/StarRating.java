package com.galvanize.entities;

import javax.persistence.*;

@Entity
public class StarRating {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;
    @Enumerated(EnumType.STRING)
    @Column
    PossibleRatings rating;

    public StarRating() {
    }

    public StarRating(PossibleRatings rating) {
        this.rating = rating;
    }

    public StarRating(long userId, PossibleRatings rating){
        this.userId = userId;
        this.rating = rating;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public PossibleRatings getRating() {
        return rating;
    }

    public void setRating(PossibleRatings rating) {
        this.rating = rating;
    }

    public enum PossibleRatings{
        ONE,TWO,THREE,FOUR,FIVE
    }
}
