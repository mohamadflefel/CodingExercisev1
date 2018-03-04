package com.flefel.CodingExercise.adapters.exceptions;

public class InvalidLengthOfStay extends Exception {
    public InvalidLengthOfStay() {
        super( "Invalid Length Of Stay Value" );
        this.printStackTrace();

    }
}
