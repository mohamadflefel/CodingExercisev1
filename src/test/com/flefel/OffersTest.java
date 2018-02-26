package com.flefel;

import com.flefel.CodingExercise.GateWay.DBGateWayJSON;
import com.flefel.CodingExercise.adapters.DBGateway;
import com.flefel.CodingExercise.adapters.OffersRequest;
import com.flefel.CodingExercise.adapters.OffersResponse;
import com.flefel.CodingExercise.entities.Hotel;
import com.flefel.CodingExercise.entities.Offers;
import com.flefel.CodingExercise.interactors.OffersUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OffersTest {

    private OffersUseCase useCase;
    private OffersRequest request;

    @Before
    public void setup() {
        useCase = new OffersUseCase(new DBGateWayJSON());
        request = new OffersRequest();
    }


    @Test
    public void givenListAllOffersThenOffersListMustNotEmpty() {

        OffersResponse response = useCase.execute(request);
        Hotel[] offers = response.getHotels();
        Assert.assertTrue(offers.length > 0);
    }
    @Test
    public void givenOfferByDestinationNameThenOffersListMustNotEmpty() {

        OffersResponse response = useCase.execute(request);
        Hotel[] offers = response.getHotels();
        Assert.assertTrue(offers.length > 0);
    }


    //https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel&destinationName=New%20Orleans
}
