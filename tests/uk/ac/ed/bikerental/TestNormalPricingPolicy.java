package uk.ac.ed.bikerental;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@DisplayName("Testing Normal Pricing Policy Class")
class TestNormalPricingPolicy {
    private NormalPricingPolicy normalPricingPolicy = new NormalPricingPolicy();
    private Collection<Bike> bikes = new ArrayList<>(); // Use as argument for calculatePrice()

    @DisplayName("Sets up daily rental prices for each normalPricingPolicy object")
    @BeforeEach
    void setup() throws Exception{
        // Instantiate bikeType and Bike objects to use to test
        BikeType bikeType1 = new BikeType("Mountain Bike", new BigDecimal(205.4));
        BikeType bikeType2 = new BikeType("Tricycle", new BigDecimal(33.3));
        BikeType bikeType3 = new BikeType("BMX", new BigDecimal(79));

        // create bikes to test with
        Bike bike1 = new Bike(
                new BigDecimal(50) , //Deposit Amount
                new Location("EH8123", "South Street"), //Location
                new ArrayList<DateRange>(), //BookedDates
                bikeType1
        );

        Bike bike2 = new Bike(
                new BigDecimal(10) ,
                new Location("EH8123", "South Street"),
                new ArrayList<DateRange>(),
                bikeType2
        );

        Bike bike3 = new Bike(
                new BigDecimal(22) ,
                new Location("EH6456", "North Street"),
                new ArrayList<DateRange>(),
                bikeType3
        );
        Bike bike4 = new Bike(
                new BigDecimal(30) ,
                new Location("EH3987", "West End"),
                new ArrayList<DateRange>(),
                bikeType3
        );

        // Add new Bike objects to bikes
        bikes.add(bike1);
        bikes.add(bike2);
        bikes.add(bike3);
        bikes.add(bike4);

        //Setting up custom daily rental prices for each bikeType
        BigDecimal dailyPrice1= new BigDecimal(40.0);
        BigDecimal dailyPrice2= new BigDecimal(3);
        BigDecimal dailyPrice3= new BigDecimal(15);
        normalPricingPolicy.setDailyRentalPrice(bikeType1, dailyPrice1);
        normalPricingPolicy.setDailyRentalPrice(bikeType2, dailyPrice2);
        normalPricingPolicy.setDailyRentalPrice(bikeType3, dailyPrice3);
    }

    @Test
    void testCalculatePrice() {
        DateRange duration = new DateRange(LocalDate.of(2019, 3, 3),
                LocalDate.of(2019, 3, 6));
        // Set expected and actual outputs
        BigDecimal expectedPrice = new BigDecimal((40.0 + 3 + 15 + 15));
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = normalPricingPolicy.calculatePrice(bikes, duration);
        // test outputs against each other
        assertEquals(expectedPrice, actualPrice);
    }

    //If information about the rental price of a requested bike is not found
    // The system should return an error. We now test if that error occurs in such a scenario
    @Test
    void throwErrorIfBikeTypeNotFound(){
        DateRange duration = new DateRange(LocalDate.of(2019, 3, 3),
                LocalDate.of(2019, 3, 6));
        //Create a bikeType that has not been assigned information about daily rental price
        BikeType nonExistantBikeType = new BikeType("BikeType with No Rental Price", new BigDecimal(3));
        Bike nonExistantBike = new Bike(
                new BigDecimal(30) ,
                new Location("EH00NO", "South East Exit Street"),
                new ArrayList<DateRange>(),
                nonExistantBikeType
        );

        Collection<Bike> bikesCopy = bikes;
        bikesCopy.add(nonExistantBike);
        assertThrows(IllegalArgumentException.class, () -> {
            normalPricingPolicy.calculatePrice(bikesCopy,duration);
        });
    }
}
