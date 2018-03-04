package com.flefel.CodingExercise.adapters.exceptions;

public class InvalidRatingValue extends Exception {
    public InvalidRatingValue() {
        super( "Invalid Value for Min Rating" );
        this.printStackTrace();

    }
}
