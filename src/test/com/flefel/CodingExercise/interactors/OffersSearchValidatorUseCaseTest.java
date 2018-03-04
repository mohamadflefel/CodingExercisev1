package com.flefel.CodingExercise.interactors;

import com.flefel.CodingExercise.GateWay.DBGateWayMock;
import com.flefel.CodingExercise.adapters.OffersSearchValidatorRequest;
import com.flefel.CodingExercise.adapters.exceptions.InvalidDateRange;
import com.flefel.CodingExercise.adapters.exceptions.InvalidLengthOfStay;
import com.flefel.CodingExercise.adapters.exceptions.InvalidRatingValue;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OffersSearchValidatorUseCaseTest {


    private OffersSearchValidatorUseCase useCase;
    private OffersSearchValidatorRequest request;

    @Before
    public void setup() {
        useCase = new OffersSearchValidatorUseCase( new DBGateWayMock() );
        request = new OffersSearchValidatorRequest();
    }


    @Test(expected = InvalidLengthOfStay.class)
    public void givenInvalidLengthOfStayThenExceptionMustReturned() throws Exception {

        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 0 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 1 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 1 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }


    @Test(expected = InvalidDateRange.class)
    public void givenInvalidDateRangeThenExceptionMustReturned() throws Exception {

        String fromDate = "2019-01-01";
        SimpleDateFormat fromDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        Calendar fromDateCalendar = Calendar.getInstance();
        fromDateCalendar.setTime( fromDateFormat.parse( fromDate ) );

        String toDate = "2018-01-01";
        SimpleDateFormat toDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        Calendar toDateCalendar = Calendar.getInstance();
        fromDateCalendar.setTime( fromDateFormat.parse( fromDate ) );


        request.setFromDate( fromDateCalendar.getTime() );
        request.setToDate( toDateCalendar.getTime() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 1 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 1 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }


    @Test(expected = InvalidRatingValue.class)
    public void givenInvalidDateMinRatingThenExceptionMustReturned() throws Exception {


        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 8 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 1 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 1 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }

    @Test(expected = InvalidRatingValue.class)
    public void givenInvalidDateMinTotalRatingThenExceptionMustReturned() throws Exception {


        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 6 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 1 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }

    @Test(expected = InvalidRatingValue.class)
    public void givenInvalidDateMinGuestRatingThenExceptionMustReturned() throws Exception {


        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 4 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 9 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }

    @Test(expected = InvalidRatingValue.class)
    public void givenInvalidDateMaxGuestRatingThenExceptionMustReturned() throws Exception {


        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 4 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 4 );
        request.setMaxGuestRating( 9 );

        useCase.execute( request );
    }

    @Test(expected = InvalidRatingValue.class)
    public void givenInvalidDateMaxTotalRatingThenExceptionMustReturned() throws Exception {


        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 4 );
        request.setMaxTotalRating( 9 );
        request.setMinGuestRating( 4 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }

    @Test(expected = InvalidRatingValue.class)
    public void givenInvalidDateMaxRatingThenExceptionMustReturned() throws Exception {


        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 1 );
        request.setMaxRating( 9 );
        request.setMinTotalRating( 4 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 4 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }

    @Test
    public void givenValidSearchParametersThenNothing() throws Exception {


        request.setFromDate( new Date() );
        request.setToDate( new Date() );
        request.setLengthOfStay( 50 );
        request.setMinRating( 1 );
        request.setMaxRating( 5 );
        request.setMinTotalRating( 4 );
        request.setMaxTotalRating( 5 );
        request.setMinGuestRating( 4 );
        request.setMaxGuestRating( 5 );

        useCase.execute( request );
    }
}
