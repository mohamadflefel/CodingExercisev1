package com.flefel.CodingExercise.ui;

import com.flefel.CodingExercise.GateWay.DBGateWayJSON;
import com.flefel.CodingExercise.adapters.OffersRequest;
import com.flefel.CodingExercise.adapters.OffersResponse;
import com.flefel.CodingExercise.entities.Hotel;
import com.flefel.CodingExercise.interactors.OffersUseCase;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_LINK;

@Theme("mytheme")
public class mainPage extends UI {


    HorizontalLayout mainLayout;
    TextField txtLocation;
    DateField fromDate;
    DateField toDate;
    TextField lengthOfStay;
    TextField minStarRate;
    TextField maxStarRate;
    TextField minTotalRate;
    TextField maxTotalRate;
    TextField minGuestRate;
    TextField maxGuestRate;


    @Override
    public void init(VaadinRequest request) {
        mainLayout = new HorizontalLayout();
        setContent( mainLayout );
        initializeControls();
        createSearchControl();
        listAllOffers();
    }

    private void initializeControls() {
        txtLocation = new TextField( "Location" );
        fromDate = new DateField( "From Date" );
        toDate = new DateField( "To Date" );
        lengthOfStay = new TextField( "Length of Stay" );
        minStarRate = new TextField( "Min Start Rate" );
        maxStarRate = new TextField( "Max Start Rate" );
        minTotalRate = new TextField( "Min Total Rate" );
        maxTotalRate = new TextField( "Max Total Rate" );
        minGuestRate = new TextField( "Min Guest Rate" );
        maxGuestRate = new TextField( "Min Guest Rate" );


    }

    private void createSearchControl() {
        VerticalLayout searchLayout = new VerticalLayout();
        searchLayout.addComponent( txtLocation );
        searchLayout.addComponent( fromDate );
        searchLayout.addComponent( toDate );
        searchLayout.addComponent( lengthOfStay );
        searchLayout.addComponent( createRatingInput( minStarRate, maxStarRate ) );
        searchLayout.addComponent( createRatingInput( minTotalRate, maxTotalRate ) );
        searchLayout.addComponent( createRatingInput( minGuestRate, maxGuestRate ) );


        Button btnSearch = new Button( "Search",
                VaadinIcons.SEARCH );
        btnSearch.addListener( new Listener() {
            @Override
            public void componentEvent(Event event) {
                btnSearchClick( event );
            }
        } );

        searchLayout.addComponent( btnSearch );
        mainLayout.addComponent( searchLayout );
    }

    private Component createRatingInput(TextField minField, TextField maxField) {
        HorizontalLayout ratingLayout = new HorizontalLayout();
        ratingLayout.addComponent( minField );
        ratingLayout.addComponent( new Label( "/" ) );
        ratingLayout.addComponent( maxField );
        return ratingLayout;
    }

    void btnSearchClick(Event event) {
        listSearchResults( txtLocation.getValue() );
    }

    private void listSearchResults(String value) {
        OffersUseCase useCase = new OffersUseCase( new DBGateWayJSON() );
        OffersResponse response = useCase.execute( new OffersRequest() );
        Hotel[] hotels = response.getHotels();
//        for (Hotel hotel : hotels) {
        mainLayout.addComponent( createHotelLayout( hotels[0] ) );
//        }
    }


    private void listAllOffers() {

        VerticalLayout offersLayout=new VerticalLayout(  );
        OffersUseCase useCase = new OffersUseCase( new DBGateWayJSON() );
        OffersResponse response = useCase.execute( new OffersRequest() );
        Hotel[] hotels = response.getHotels();
        for (Hotel hotel : hotels) {
            offersLayout.addComponent( createHotelLayout( hotel ) );
        }
        mainLayout.addComponent( offersLayout );
    }

    private Component createHotelLayout(Hotel hotel) {
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout leftLayout = new HorizontalLayout();
        HorizontalLayout middleLayout = new HorizontalLayout();
        HorizontalLayout rightLayout = new HorizontalLayout();

        layout.addComponent( leftLayout );
        layout.addComponent( middleLayout );
        layout.addComponent( rightLayout );

        createHotelImage( hotel, leftLayout );
        createHotelNameAndLocation( hotel, middleLayout );
        createHotelPrice( hotel, leftLayout );

        return layout;
    }

    private void createHotelPrice(Hotel hotel, HorizontalLayout leftLayout) {
        double originalPrice = Double.valueOf( hotel.getHotelPricingInfo().getOriginalPricePerNight() );
        double percent = Double.valueOf( hotel.getHotelPricingInfo().getPercentSavings() );
        double offerPrice = originalPrice - (originalPrice * percent / 100);

        Label oldPrice = new Label( "Old Price :" + originalPrice );
        oldPrice.setStyleName( ValoTheme.LABEL_FAILURE );

        Label newPrice = new Label( "New Price :" + Math.round( offerPrice * 100.0 ) / 100.0 );
        newPrice.setStyleName( ValoTheme.LABEL_SUCCESS );

        leftLayout.addComponent( oldPrice );
        leftLayout.addComponent( newPrice );
    }

    private void createHotelNameAndLocation(Hotel hotel, HorizontalLayout middleLayout) {
        Label hotelName = new Label( hotel.getHotelInfo().getHotelName() );
        Label hotelCity = new Label( hotel.getHotelInfo().getHotelCity() );
        Link hotelLocation = new Link( "Location",
                new ExternalResource( "http://www.google.com/maps/place/" + hotel.getHotelInfo().getHotelLatitude() + "," + hotel.getHotelInfo().getHotelLongitude() + "" ) );

        hotelLocation.setStyleName( BUTTON_LINK );

        middleLayout.addComponent( hotelName );
        middleLayout.addComponent( hotelCity );
        middleLayout.addComponent( hotelLocation );
    }

    private void createHotelImage(Hotel hotel, HorizontalLayout leftLayout1) {
        Image hotelImage = new Image();
        hotelImage.setSource( new ExternalResource( hotel.getHotelInfo().getHotelImageUrl() ) );

        leftLayout1.addComponent( hotelImage );
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = mainPage.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}