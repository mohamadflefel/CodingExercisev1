package com.flefel.CodingExercise.adapters;

import com.flefel.CodingExercise.entities.Hotel;

public interface DBGateway {
    Hotel[] listAllHotels();

    Hotel[] listHotelsByWhere(String s);
}
