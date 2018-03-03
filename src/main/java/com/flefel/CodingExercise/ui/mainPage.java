package com.flefel.CodingExercise.ui;

import com.flefel.CodingExercise.GateWay.DBGateWayJSON;
import com.flefel.CodingExercise.adapters.OffersRequest;
import com.flefel.CodingExercise.adapters.OffersResponse;
import com.flefel.CodingExercise.entities.Hotel;
import com.flefel.CodingExercise.interactors.OffersUseCase;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_LINK;

public class mainPage extends UI {


    VerticalLayout layout;

    @Override
    public void init(VaadinRequest request) {
        layout = new VerticalLayout();
        setContent( layout );

        listAllOffers();
    }

    private void listAllOffers() {
        OffersUseCase useCase = new OffersUseCase( new DBGateWayJSON() );
        OffersResponse response = useCase.execute( new OffersRequest() );
        Hotel[] hotels = response.getHotels();
        for (Hotel hotel : hotels) {
            layout.addComponent( createHotelLayout( hotel ) );
        }
    }

    private Component createHotelLayout(Hotel hotel) {
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout leftLayout1 = new HorizontalLayout();
        HorizontalLayout middleLayout = new HorizontalLayout();
        HorizontalLayout rightLayout = new HorizontalLayout();

        layout.addComponent( leftLayout1 );
        layout.addComponent( middleLayout );
        layout.addComponent( rightLayout );

        Image hotelImage = new Image();
        hotelImage.setSource( new ExternalResource( hotel.getHotelInfo().getHotelImageUrl() ) );
        leftLayout1.addComponent( hotelImage );


        Label hotelName = new Label( hotel.getHotelInfo().getHotelName() );
        Label hotelCity = new Label( hotel.getHotelInfo().getHotelCity() );
        Link hotelLocation = new Link( "Location",
                new ExternalResource( "http://www.google.com/maps/place/" + hotel.getHotelInfo().getHotelLatitude() + "," + hotel.getHotelInfo().getHotelLongitude() + "" ) );

        hotelLocation.setStyleName( BUTTON_LINK );

        middleLayout.addComponent( hotelName );
        middleLayout.addComponent( hotelCity );
        middleLayout.addComponent( hotelLocation );


        return layout;
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = mainPage.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}