package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testing policy classes implementing the interface PricingPolicy")
public class PricingPolicyTests {
    // You can add attributes here
    private PricingPolicy normalPricingPolicy= new NormalPricingPolicy();
    private PricingPolicy discount = new DiscountPolicy();
    private Collection<Bike> bikes = new ArrayList<>();

    @DisplayName("Sets up daily rental prices for both instances")
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

        discount.setDailyRentalPrice(bikeType1, dailyPrice1);
        discount.setDailyRentalPrice(bikeType2, dailyPrice2);
        discount.setDailyRentalPrice(bikeType3, dailyPrice3);
    }

    //TESTING NORMAL PRICING POLICY//
    @Test
    void testCalculatePriceNormalPricingPolicy() {
        DateRange duration = new DateRange(LocalDate.of(2019, 3, 3),
                LocalDate.of(2019, 3, 6));
        // Set expected and actual outputs
        BigDecimal expectedPrice = new BigDecimal((40.0 + 3 + 15 + 15));
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = normalPricingPolicy.calculatePrice(bikes, duration);
        // test outputs against each other
        assertEquals(expectedPrice, actualPrice);
    }

    //TESTING DISCOUNT POLICY//
    // test hiring duration of 1-2 days
    @Test
    void testCalculatePriceDiscountPolicy1(){
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
    void testCalculatePriceDiscountPolicy2() {
        DateRange duration = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,6  )); //3-6 days

        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*0.95);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        assertEquals(expectedPrice, actualPrice);
    }

    // test hiring duration of 7-13 days
    @Test
    void testCalculatePriceDiscountPolicy3() {
        DateRange duration = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,11  )); //7-13 days

        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*0.9);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        assertEquals(expectedPrice, actualPrice);
    }

    // test hiring duration of 14+ days
    @Test
    void testCalculatePriceDiscountPolicy4() {
        DateRange duration = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,5,4  )); // 14+ days

        BigDecimal expectedPrice = new BigDecimal((40.0+3+15+15)*0.85);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = discount.calculatePrice(bikes, duration);

        assertEquals(expectedPrice, actualPrice);
    }

    // test hiring duration with dates that wrap to a new year
    @Test
    void testCalculatePriceDiscountPolicy5() {
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
    
    //CHECKING BOTH CLASSES//

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
        assertThrows(IllegalArgumentException.class, () -> {
            discount.calculatePrice(bikesCopy,duration);
        });
    }
}