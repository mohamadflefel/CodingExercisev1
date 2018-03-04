package com.flefel.CodingExercise.GateWay;

import com.flefel.CodingExercise.adapters.DBGateway;
import com.flefel.CodingExercise.entities.Hotel;

public class DBGateWayMock implements DBGateway {
    public Hotel[] listAllHotels() {

        Hotel[] hotels = new Hotel[1];
        hotels[0] = new Hotel();

        return hotels;


    }

    @Override
    public Hotel[] listHotelsByWhere(String whereStatement) {
        if (whereStatement.contains( "Test Location" ))
            return new Hotel[1];
        else
            return new Hotel[0];
    }


}
