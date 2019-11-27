package uk.ac.ed.bikerental;

//import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@DisplayName("Testing Discount Policy Class")
class TestDiscountPolicy {
    private DiscountPolicy discount = new DiscountPolicy();
    private Collection<Bike> bikes = new ArrayList<>(); // Use as argument for calculatePrice()

    @DisplayName("Sets up daily rental prices for each DiscountPolicy object")
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
        //add another bike with the same biketype, but is in a different location
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

        discount.setDailyRentalPrice(bikeType1, dailyPrice1);
        discount.setDailyRentalPrice(bikeType2, dailyPrice2);
        discount.setDailyRentalPrice(bikeType3, dailyPrice3);
    }

    // test hiring duration of 1-2 days
    @Test
    void test1(){
        // set date ranges to use to test
        DateRange duration = new DateRange(LocalDate.of(2019,3,2),
                LocalDate.of(2019,3,3  )); //1-2 days

        // Set expected and actual outputs
        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*1);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        // test outputs against each other
        assertEquals(expectedPrice, actualPrice);
    }

    // test hiring duration of 3-6 days
    @Test
    void test2() {
        DateRange duration = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,6  )); //3-6 days

        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*0.95);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        assertEquals(expectedPrice, actualPrice);
    }

    // test hiring duration of 7-13 days
    @Test
    void test3() {
        DateRange duration = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,11  )); //7-13 days

        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*0.9);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        assertEquals(expectedPrice, actualPrice);
    }

    // test hiring duration of 14+ days
    @Test
    void test4() {
        DateRange duration = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,5,4  )); // 14+ days

        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*0.85);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        assertEquals(expectedPrice, actualPrice);
    }

    // test hiring duration with dates that wrap to a new year
    @Test
    void test5() {
        DateRange duration = new DateRange(LocalDate.of(2019,12,30),
                LocalDate.of(2020,1,3  )); //dateRange wraps round year

        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*0.95);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testExpectedException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DateRange duration = new DateRange(LocalDate.of(2019,3,3),
                    LocalDate.of(2019,3,3  )); // 0 days

            discount.calculatePrice(bikes, duration);
        });
    }
}
