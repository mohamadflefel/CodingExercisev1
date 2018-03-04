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
        if (request.getIsListAll())
            response.setHotels( gateway.listAllHotels() );
        else
            response.setHotels( gateway.listHotelsByWhere( buildWhereStatement( request ) ) );

        return response;
    }

    private String buildWhereStatement(OffersRequest request) {
        StringBuilder whereStatement = new StringBuilder();
        if (request.getLocation() != null)
            whereStatement.append( "&destinationName=" ).append( request.getLocation() );
        if (request.getFromDate() != null)
            whereStatement.append( "&minTripStartDate=" ).append( request.getLocation() );
        if (request.getToDate() != null)
            whereStatement.append( "&maxTripStartDate=" ).append( request.getLocation() );
        if (request.getLengthOfStay() != 0)
            whereStatement.append( "&lengthOfStay=" ).append( request.getLengthOfStay() );
        if (request.getMinRating() != 0)
            whereStatement.append( "&minStarRating=" ).append( request.getMinRating() );
        if (request.getMaxRating() != 0)
            whereStatement.append( "&maxStarRating=" ).append( request.getMaxRating() );

        if (request.getMinTotalRating() != 0)
            whereStatement.append( "&minTotalRate=" ).append( request.getMinTotalRating() );
        if (request.getMaxTotalRating() != 0)
            whereStatement.append( "&maxTotalRate=" ).append( request.getMaxTotalRating() );

        if (request.getMinGuestRating() != 0)
            whereStatement.append( "&minGuestRating=" ).append( request.getMinGuestRating() );
        if (request.getMaxGuestRating() != 0)
            whereStatement.append( "&maxGuestRating=" ).append( request.getMaxGuestRating() );


        return whereStatement.toString();

    }
}
