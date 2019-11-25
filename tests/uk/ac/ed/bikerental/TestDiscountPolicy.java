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
    private DiscountPolicy discount1 = new DiscountPolicy();
    //private DiscountPolicy discount2 = new DiscountPolicy();
    private Collection<Bike> bikes = new ArrayList<>();

    @DisplayName("Sets up daily rental prices for each DiscountPolicy object")
    @BeforeEach
    void setup() throws Exception{
        BikeType bikeType1 = new BikeType("Mountain Bike", new BigDecimal(205.4));
        BikeType bikeType2 = new BikeType("Tricycle", new BigDecimal(33.3));
        BikeType bikeType3 = new BikeType("BMX", new BigDecimal(79));
        BikeType bikeType4 = new BikeType("City Bike", new BigDecimal(112));

        Bike bike1 = new Bike(
                new BigDecimal(50) , //Deposit Amount
                new Location("EH8123", "South Street"), //Location
                new Integer(1), //ProviderID
                new ArrayList<DateRange>(), //BookedDates
                bikeType1
        );

        Bike bike2 = new Bike(
                new BigDecimal(10) ,
                new Location("EH8123", "South Street"),
                new Integer(1),
                new ArrayList<DateRange>(),
                bikeType2
        );

        Bike bike3 = new Bike(
                new BigDecimal(22) ,
                new Location("EH6456", "North Street"),
                new Integer(1),
                new ArrayList<DateRange>(),
                bikeType3
        );
        Bike bike4 = new Bike(
                new BigDecimal(30) ,
                new Location("EH3987", "West End"),
                new Integer(1),
                new ArrayList<DateRange>(),
                bikeType4
        );

        bikes.add(bike1);
        bikes.add(bike2);
        bikes.add(bike3);
        bikes.add(bike4);

        //Setting up custom daily rental prices for each DiscountPolicy object
        BigDecimal dailyPrice1= new BigDecimal(40.0);
        BigDecimal dailyPrice2= new BigDecimal(3);
        BigDecimal dailyPrice3= new BigDecimal(15);
        BigDecimal dailyPrice4= new BigDecimal(22);

        discount1.setDailyRentalPrice(bikeType1, dailyPrice1);
        discount1.setDailyRentalPrice(bikeType2, dailyPrice2);
        discount1.setDailyRentalPrice(bikeType3, dailyPrice3);

        //discount2.setDailyRentalPrice(bikeType1, dailyPrice1);
        //discount2.setDailyRentalPrice(bikeType2, dailyPrice2);
        //discount2.setDailyRentalPrice(bikeType3, dailyPrice3);
        //discount2.setDailyRentalPrice(bikeType4, dailyPrice4);
    }

    @Test
    void testCalculatePrice(){
        DateRange duration1 = new DateRange(LocalDate.of(2019,3,2),
                LocalDate.of(2019,3,3  )); //1-2 days

        DateRange duration2 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,6  )); //3-6 days

        DateRange duration3 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,11  )); //7-13 days

        DateRange duration4 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,5,4  )); // 14+ days

        DateRange duration5 = new DateRange(LocalDate.of(2019,12,30),
                LocalDate.of(2020,1,3  )); //dateRange wraps round year


        //System.out.println(totalPrice1);

        //totalPrice2 =  totalPrice2.multiply(discount2.calculatePrice(bikes, duration1));
        //totalPrice2 =  totalPrice2.multiply(discount2.calculatePrice(bikes, duration2));
        //totalPrice2 =  totalPrice2.multiply(discount2.calculatePrice(bikes, duration3));
        //totalPrice2 =  totalPrice2.multiply(discount2.calculatePrice(bikes, duration4));

        BigDecimal expectedPrice1 = new BigDecimal((40.0+3+15)*1);
        expectedPrice1 = expectedPrice1.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice1 = discount1.calculatePrice(bikes, duration1);

        BigDecimal expectedPrice2 = new BigDecimal((40.0+3+15)*0.95);
        expectedPrice2 = expectedPrice2.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice2 = discount1.calculatePrice(bikes, duration2);

        BigDecimal expectedPrice3 = new BigDecimal((40.0+3+15)*0.9);
        expectedPrice3 = expectedPrice3.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice3 = discount1.calculatePrice(bikes, duration3);

        BigDecimal expectedPrice4 = new BigDecimal((40.0+3+15)*0.85);
        expectedPrice4 = expectedPrice4.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice4 = discount1.calculatePrice(bikes, duration4);

        BigDecimal expectedPrice5 = new BigDecimal((40.0+3+15)*0.95);
        expectedPrice5 = expectedPrice5.setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice5 = discount1.calculatePrice(bikes, duration5);

        assertEquals(expectedPrice1, actualPrice1);
        assertEquals(expectedPrice2, actualPrice2);
        assertEquals(expectedPrice3, actualPrice3);
        assertEquals(expectedPrice4, actualPrice4);
        assertEquals(expectedPrice5, actualPrice5);
    }
}
