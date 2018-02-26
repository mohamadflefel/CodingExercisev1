package com.flefel;

import com.flefel.CodingExercise.GateWay.DBGateWayJSON;
import com.flefel.CodingExercise.adapters.DBGateway;
import com.flefel.CodingExercise.adapters.OffersRequest;
import com.flefel.CodingExercise.adapters.OffersResponse;
import com.flefel.CodingExercise.entities.Hotel;
import com.flefel.CodingExercise.entities.Offers;
import com.flefel.CodingExercise.interactors.OffersUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OffersTest {

    @Test
    public void givenListAllOffersThenOffersListMustNotEmpty() {

        DBGateway gateway = new DBGateWayJSON();
        OffersUseCase useCase = new OffersUseCase(gateway);
        OffersRequest request = new OffersRequest();
        OffersResponse response = useCase.execute(request);
        Hotel[] offers = response.getHotels();
        Assert.assertTrue(offers.length > 0);
    }
}
