package com.flefel.CodingExercise.interactors;

import com.flefel.CodingExercise.GateWay.DBGateWayJSON;
import com.flefel.CodingExercise.GateWay.DBGateWayMock;
import com.flefel.CodingExercise.adapters.DBGateway;
import com.flefel.CodingExercise.adapters.OffersRequest;
import com.flefel.CodingExercise.adapters.OffersResponse;
import com.flefel.CodingExercise.entities.Hotel;
import com.flefel.CodingExercise.entities.Offers;
import com.flefel.CodingExercise.interactors.OffersUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class OffersUseCaseTest {

    private OffersUseCase useCase;
    private OffersRequest request;

    @Before
    public void setup() {
        useCase = new OffersUseCase( new DBGateWayMock() );
        request = new OffersRequest();
    }


    @Test
    public void givenListAllOffersThenOffersListMustNotEmpty() {

        request.setListAll( true );
        OffersResponse response = useCase.execute( request );
        Hotel[] offers = response.getHotels();
        Assert.assertTrue( offers.length > 0 );
    }

    @Test
    public void givenOfferByExistingDestinationNameThenOffersListMustNotEmpty() {

        request.setLocation( "Test Location" );
        OffersResponse response = useCase.execute( request );
        Hotel[] offers = response.getHotels();
        Assert.assertTrue( offers.length > 0 );
    }


    @Test
    public void givenOfferByNotExistingDestinationNameThenOffersListMustBeEmpty() {

        request.setLocation( "Test undefined Location" );

        OffersResponse response = useCase.execute( request );
        Hotel[] offers = response.getHotels();
        Assert.assertEquals( 0, offers.length );
    }


    @Test
    public void givenOfferByExistingParametersThenOffersListMustBeEmpty() {

        request.setLocation( "Test undefined Location" );
        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 4 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 1 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 1 );
        request.setMaxGuestRating( 5 );

        OffersResponse response = useCase.execute( request );
        Hotel[] offers = response.getHotels();
        Assert.assertEquals( 0, offers.length );
    }


}
