package com.flefel.CodingExercise.ui;

import com.flefel.CodingExercise.GateWay.DBGateWayJSON;
import com.flefel.CodingExercise.adapters.OfferParametersBase;
import com.flefel.CodingExercise.adapters.OffersRequest;
import com.flefel.CodingExercise.adapters.OffersResponse;
import com.flefel.CodingExercise.adapters.OffersSearchValidatorRequest;
import com.flefel.CodingExercise.entities.Hotel;
import com.flefel.CodingExercise.interactors.OffersSearchValidatorUseCase;
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

import java.time.ZoneId;
import java.util.Date;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_LINK;

@Theme("mytheme")
public class mainPage extends UI {


    private HorizontalLayout mainLayout;
    private TextField txtLocation;
    private InlineDateField fromDate;
    private InlineDateField toDate;
    private TextField lengthOfStay;
    private TextField minStarRate;
    private TextField maxStarRate;
    private TextField minTotalRate;
    private TextField maxTotalRate;
    private TextField minGuestRate;
    private TextField maxGuestRate;


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
        fromDate = new InlineDateField( "From Date" );
        toDate = new InlineDateField( "To Date" );
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

    private void btnSearchClick(Event event) {
        listSearchResults( txtLocation.getValue() );
    }

    private void listSearchResults(String value) {
        validateSearchParameters();
        bindFilterResults();


    }

    private void validateSearchParameters() {

        try {
            OffersSearchValidatorUseCase useCase = new OffersSearchValidatorUseCase( new DBGateWayJSON() );

            OffersSearchValidatorRequest request = new OffersSearchValidatorRequest();

            bindRequestParameters( request );

            useCase.execute( request );
        } catch (Exception e) {
            Notification.show( "Invalid Search Values",
                    e.getMessage(),
                    Notification.Type.HUMANIZED_MESSAGE );
        }

    }

    private void bindFilterResults() {

        OffersUseCase useCase = new OffersUseCase( new DBGateWayJSON() );

        OffersRequest request = new OffersRequest();

        bindRequestParameters( request );

        OffersResponse response = useCase.execute( request );
        bindOffers( response.getHotels() );
    }

    private void bindRequestParameters(OfferParametersBase request) {
        request.setFromDate( Date.from( fromDate.getValue().atStartOfDay( ZoneId.systemDefault() ).toInstant() ) );
        request.setToDate( Date.from( toDate.getValue().atStartOfDay( ZoneId.systemDefault() ).toInstant() ) );
        request.setLengthOfStay( getIntegerFieldValue( lengthOfStay.getValue() ) );
        request.setMinRating( getIntegerFieldValue( minStarRate.getValue() ) );
        request.setMaxRating( getIntegerFieldValue( maxStarRate.getValue() ) );
        request.setMinTotalRating( getIntegerFieldValue( minTotalRate.getValue() ) );
        request.setMaxTotalRating( getIntegerFieldValue( maxTotalRate.getValue() ) );
        request.setMinGuestRating( getIntegerFieldValue( minGuestRate.getValue() ) );
        request.setMaxGuestRating( getIntegerFieldValue( maxGuestRate.getValue() ) );
    }

    private int getIntegerFieldValue(String value) {
        return Integer.parseInt( "".equals( value ) ? "0" : value );
    }

    private VerticalLayout offersLayout;

    private void listAllOffers() {

        OffersUseCase useCase = new OffersUseCase( new DBGateWayJSON() );
        OffersRequest request = new OffersRequest();
        request.setListAll( true );
        OffersResponse response = useCase.execute( request );
        bindOffers( response.getHotels() );
        mainLayout.addComponent( offersLayout );
    }

    private void bindOffers(Hotel[] hotels) {
        offersLayout = new VerticalLayout();

        for (Hotel hotel : hotels) {
            offersLayout.addComponent( createHotelLayout( hotel ) );
        }
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