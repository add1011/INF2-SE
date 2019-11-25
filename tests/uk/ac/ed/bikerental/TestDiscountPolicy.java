package uk.ac.ed.bikerental;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;




@DisplayName("Testing Discount Policy Class")
class TestDiscountPolicy {
    private DiscountPolicy discount1 = new DiscountPolicy();
    private DiscountPolicy discount2 = new DiscountPolicy();
    private DiscountPolicy discount3 = new DiscountPolicy();
    private DiscountPolicy discount4 = new DiscountPolicy();

    private BigDecimal totalDailyPrice = new BigDecimal(0);



    @DisplayName("Sets up daily rental prices for each DiscountPolicy object")
    @BeforeEach
    void setup() throws Exception{
        BikeType bikeType1 = new BikeType("Mountain Bike", new BigDecimal(205.4));
        BikeType bikeType2 = new BikeType("Tricycle", new BigDecimal(33.3));
        BikeType bikeType3 = new BikeType("BMX", new BigDecimal(79));
        BikeType bikeType4 = new BikeType("City Bike", new BigDecimal(112));

        //Setting up custom daily rental prices for each DiscountPolicy object
        BigDecimal dailyPrice1= new BigDecimal(40.0);
        BigDecimal dailyPrice2= new BigDecimal(3);
        BigDecimal dailyPrice3= new BigDecimal(15);
        BigDecimal dailyPrice4= new BigDecimal(22);


        discount1.setDailyRentalPrice(bikeType1, dailyPrice1);
        discount2.setDailyRentalPrice(bikeType2, dailyPrice2);
        discount3.setDailyRentalPrice(bikeType3, dailyPrice3);
        discount4.setDailyRentalPrice(bikeType4, dailyPrice4);

        totalDailyPrice.add(dailyPrice1);
        totalDailyPrice.add(dailyPrice2);
        totalDailyPrice.add(dailyPrice3);
        totalDailyPrice.add(dailyPrice4);




    }

    private BigDecimal setTotalPrice(@NotNull Collection<Bike> bikes, DiscountPolicy discount) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Bike bike : bikes) {
            BigDecimal bikeTypeDailyRentalPrice = discount.getDailyRentalPrice().get(bike.getType());
            System.out.println(bikeTypeDailyRentalPrice);
            totalPrice = totalPrice.add(bikeTypeDailyRentalPrice);
        }
        return totalPrice;
    }



    @Test
    void testCalculatePrice(){
        DateRange duration1 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,2  )); //1-2 days

        DateRange duration2 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,6  )); //3-6 days

        DateRange duration3 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,4  )); //7-13 days

        DateRange duration4 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,5,4  )); // 14+ days
        DateRange duration5 = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,3  )); //0 days


        BigDecimal totalPrice1 = new BigDecimal(0);
        BigDecimal totalPrice2 = new BigDecimal(0);
        BigDecimal totalPrice3 = new BigDecimal(0);
        BigDecimal totalPrice4 = new BigDecimal(0);


        Bike bike1 = new Bike(
                new BigDecimal(50) , //Deposit Amount
                new Location("EH8123", "South Street"), //Location
                new Integer(1), //ProviderID
                new ArrayList<DateRange>() //BookedDates
        );

        Bike bike2 = new Bike(
                new BigDecimal(10) ,
                new Location("EH8123", "South Street"),
                new Integer(1),
                new ArrayList<DateRange>()
        );

        Bike bike3 = new Bike(
                new BigDecimal(22) ,
                new Location("EH6456", "North Street"),
                new Integer(1),
                new ArrayList<DateRange>()
        );
        Bike bike4 = new Bike(
                new BigDecimal(30) ,
                new Location("EH3987", "West End"),
                new Integer(1),
                new ArrayList<DateRange>()
        );

        Collection<Bike> bikes = new ArrayList<>();
        bikes.add(bike1);
        bikes.add(bike2);
        bikes.add(bike3);
        bikes.add(bike4);


        totalPrice1.add(setTotalPrice(bikes, discount1));
        totalPrice2.add(setTotalPrice(bikes, discount2));
        totalPrice3.add(setTotalPrice(bikes, discount3));
        totalPrice4.add(setTotalPrice(bikes, discount4));


        totalPrice1.multiply(discount1.calculatePrice(bikes, duration1));
        totalPrice2.multiply(discount2.calculatePrice(bikes, duration2));
        totalPrice3.multiply(discount3.calculatePrice(bikes, duration3));
        totalPrice4.multiply(discount4.calculatePrice(bikes, duration4));



        assertEquals(totalPrice1,totalDailyPrice.multiply(new BigDecimal(1) ) );
        assertEquals(totalPrice2,totalDailyPrice.multiply(new BigDecimal(0.95) ) );
        assertEquals(totalPrice3,totalDailyPrice.multiply(new BigDecimal(0.90) ) );
        assertEquals(totalPrice4,totalDailyPrice.multiply(new BigDecimal(0.85) ) );




    }


}
