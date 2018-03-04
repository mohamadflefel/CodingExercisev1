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
import java.util.logging.Logger;

public class DBGateWayJSON implements DBGateway {
    public Hotel[] listAllHotels() {

        Hotel[] result = getOfferInfo( "" ).getOffers().getHotel();
        return result == null ? new Hotel[0] : result;


    }

    private static final Logger LOGGER = Logger.getLogger( DBGateWayJSON.class.getName() );

    @Override
    public Hotel[] listHotelsByWhere(String filter) {
        return getOfferInfo( filter ).getOffers().getHotel();
    }

    private OfferInfo getOfferInfo(String filter) {
        OfferInfo offerInfo = new OfferInfo();
        try {

            URL url = null; // URL to Parse
            url = new URL( "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel" + filter );
            LOGGER.info( "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel" + filter );
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader( new InputStreamReader( yc.getInputStream() ) );

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //JSONArray a = (JSONArray) parser.parse(inputLine);

                Gson gson = new Gson();
                Type listType = new TypeToken<Offers>() {
                }.getType();
                offerInfo = gson.fromJson( inputLine, OfferInfo.class );

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return offerInfo;
    }

}
