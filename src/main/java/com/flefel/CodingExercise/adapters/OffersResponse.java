package com.flefel.CodingExercise.adapters;

import com.flefel.CodingExercise.entities.Hotel;

import java.util.List;

public class OffersResponse {
    private Hotel[] hotels;

    public Hotel[] getHotels() {
        return hotels;
    }

    public void setHotels(Hotel[] hotels) {
        this.hotels = hotels;
    }
}
