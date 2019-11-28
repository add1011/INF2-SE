package uk.ac.ed.bikerental;

//import org.jetbrains.annotations.NotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing Quote Class")
class TestQuote {

    private Location Edinburgh = new Location("EH12NG", "CastleHill");

    // create bike types to use
    private BikeType mountainBike = new BikeType("Mountain Bike", new BigDecimal(205.4));
    private BikeType roadBike = new BikeType("Road Bike", new BigDecimal(100));

    private Provider provider = new Provider("Bikes'R'Us", Edinburgh,
            new BigDecimal(0.8), new DiscountPolicy());

    //addBike method adds bike to the list of bikes stored with a provider and returns that bike
    private Bike bike1 = provider.addBike(mountainBike);
    private Bike bike2 = provider.addBike(roadBike);

    @DisplayName("Sets up things needed to initialise Quote with")
    @BeforeEach
    void setup() throws Exception{
        // set up the pricing policies
        provider.getPricingPolicy().setDailyRentalPrice(mountainBike, new BigDecimal(50));
        provider.getPricingPolicy().setDailyRentalPrice(roadBike, new BigDecimal(45.50));
    }

    // test hiring duration of 1-2 days
    @Test
    void testQuote(){
        // create objects to use to instantiate Quote with
        DateRange dates = new DateRange(LocalDate.of(2019,3,7),
                LocalDate.of(2019,3,10  ));
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bike1);
        bikes.add(bike2);

        // get the Quote the system generates
        Quote actualOutput = new Quote(provider, bikes, dates, Edinburgh);

        //test that the Quote generated has all the right values (and so also check that the methods work)
        assertEquals(provider, actualOutput.getProvider());
        assertEquals(Edinburgh, actualOutput.getProviderLocation());
        assertEquals(dates, actualOutput.getSelectedDates());
        assertEquals(bikes, actualOutput.getBikes());
        assertEquals(new BigDecimal(95.50*0.95).setScale(2, RoundingMode.HALF_UP), actualOutput.getTotalPrice());
        assertEquals(new BigDecimal(305.4*0.8).setScale(2, RoundingMode.HALF_UP), actualOutput.getTotalDeposit());
    }
}
