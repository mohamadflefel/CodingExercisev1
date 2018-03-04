package com.flefel.CodingExercise.interactors;

import com.flefel.CodingExercise.adapters.DBGateway;
import com.flefel.CodingExercise.adapters.OffersSearchValidatorRequest;
import com.flefel.CodingExercise.adapters.exceptions.InvalidDateRange;
import com.flefel.CodingExercise.adapters.exceptions.InvalidLengthOfStay;
import com.flefel.CodingExercise.adapters.exceptions.InvalidRatingValue;


public class OffersSearchValidatorUseCase {
    private DBGateway gateWay;

    public OffersSearchValidatorUseCase(DBGateway gateWay) {

        this.gateWay = gateWay;
    }

    public void execute(OffersSearchValidatorRequest request) throws Exception {


        if (request.getLengthOfStay() <= 0)
            throw new InvalidLengthOfStay();
        if (request.getFromDate().after( request.getToDate() ))
            throw new InvalidDateRange();
        if (request.getMinRating()<0 || request.getMinRating()>5)
            throw new InvalidRatingValue();
        if (request.getMinGuestRating()<0 || request.getMinGuestRating()>5)
            throw new InvalidRatingValue();
        if (request.getMinTotalRating()<0 || request.getMinTotalRating()>5)
            throw new InvalidRatingValue();
        if (request.getMaxGuestRating()<0 || request.getMaxGuestRating()>5)
            throw new InvalidRatingValue();
        if (request.getMaxRating()<0 || request.getMaxRating()>5)
            throw new InvalidRatingValue();
        if (request.getMaxTotalRating()<0 || request.getMaxTotalRating()>5)
            throw new InvalidRatingValue();




    }
}
