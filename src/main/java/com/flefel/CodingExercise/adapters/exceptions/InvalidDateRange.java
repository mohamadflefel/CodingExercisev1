package com.flefel.CodingExercise.adapters.exceptions;

public class InvalidDateRange  extends Exception {
    public InvalidDateRange() {
        super( "Invalid Date Range" );
        this.printStackTrace();

    }
}
