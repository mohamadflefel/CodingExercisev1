package com.flefel.CodingExercise.GateWay;

import com.flefel.CodingExercise.adapters.DBGateway;
import com.flefel.CodingExercise.entities.Hotel;
import com.flefel.CodingExercise.entities.OfferInfo;
import com.flefel.CodingExercise.entities.Offers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
