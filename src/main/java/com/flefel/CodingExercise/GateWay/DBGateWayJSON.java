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

public class DBGateWayJSON implements DBGateway {
    public Hotel[] listAllHotels() {

      return   getOfferInfo().getOffers().getHotel();


    }

    private OfferInfo getOfferInfo() {
        OfferInfo offerInfo = new OfferInfo();
        try {

            URL oracle = null; // URL to Parse
            oracle = new URL("https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel");

            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //JSONArray a = (JSONArray) parser.parse(inputLine);

                Gson gson = new Gson();
                Type listType = new TypeToken<Offers>() {
                }.getType();
                offerInfo = gson.fromJson(inputLine, OfferInfo.class);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return offerInfo;
    }

}
