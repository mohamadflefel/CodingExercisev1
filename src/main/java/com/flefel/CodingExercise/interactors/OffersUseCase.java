package com.flefel.CodingExercise.interactors;

import com.flefel.CodingExercise.adapters.DBGateway;
import com.flefel.CodingExercise.adapters.OffersRequest;
import com.flefel.CodingExercise.adapters.OffersResponse;

public class OffersUseCase {
    private DBGateway gateway;

    public OffersUseCase(DBGateway gateway) {

        this.gateway = gateway;
    }

    public OffersResponse execute(OffersRequest request) {


        OffersResponse response = new OffersResponse();
        response.setHotels(gateway.listAllHotels());
        return response;
    }
}
