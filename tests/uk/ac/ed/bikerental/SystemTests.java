package uk.ac.ed.bikerental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SystemTests {
    // You can add attributes here
    private BookingSystem bookingSystem;
    // add locations for providers
    private Location EdinburghA;
    private Location EdinburghB;
    private Location GlasgowA;

    // create bike types to use
    private BikeType mountainBike;
    private BikeType Tricycle;
    private BikeType BMX;
    private BikeType roadBike;

    // instantiate providers
    private Provider providerA;
    private Provider providerB;
    private Provider providerC;

    //addBike method adds bike to the list of bikes stored with a provider and returns that bike
    private Bike bikeA1;
    private Bike bikeA2;
    private Bike bikeA3;
    private Bike bikeA4;

    private Bike bikeB1;
    private Bike bikeB2;
    private Bike bikeB3;

    private Bike bikeC1;
    private Bike bikeC2;
    private Bike bikeC3;
    private Bike bikeC4;

    // create customer details that would be given by the customer
    private CustomerDetails customer1;
    private CustomerDetails customer2;

    // we created the quotes in each test instead of in the setup so it's easier to see which quote
    // belongs to which test

    @BeforeEach
    void setUp() throws Exception {
        DeliveryServiceFactory.setupMockDeliveryService();

        // give all object values before each tests
        bookingSystem = new BookingSystem();

        EdinburghA = new Location("EH12NG", "CastleHill");
        EdinburghB = new Location("EH91UU", "Causewayside");
        GlasgowA = new Location("G58SG", "Houston Place");

        mountainBike = new BikeType("Mountain Bike", new BigDecimal(205.4));
        Tricycle = new BikeType("Tricycle", new BigDecimal(33.3));
        BMX = new BikeType("BMX", new BigDecimal(79));
        roadBike = new BikeType("Road Bike", new BigDecimal(100));

        providerA = new Provider("Bikes'R'Us", EdinburghA,
                new BigDecimal(0.8), new DiscountPolicy());
        providerB = new Provider("The Bike Station", EdinburghB,
                new BigDecimal(1.1), new DiscountPolicy());
        providerC = new Provider("Dat Bike Place", GlasgowA,
                new BigDecimal(1), new NormalPricingPolicy());

        bikeA1 = providerA.addBike(mountainBike);
        bikeA2 = providerA.addBike(mountainBike);
        bikeA3 = providerA.addBike(Tricycle);
        bikeA4 = providerA.addBike(roadBike);

        bikeB1 = providerB.addBike(mountainBike);
        bikeB2 = providerB.addBike(BMX);
        bikeB3 = providerB.addBike(roadBike);

        bikeC1 = providerC.addBike(Tricycle);
        bikeC2 = providerC.addBike(Tricycle);
        bikeC3 = providerC.addBike(roadBike);
        bikeC4 = providerC.addBike(roadBike);

        customer1 = new CustomerDetails("Imaginary", "John",
                "Somewhere in Scotland", "EH10BB", "02385738743");
        customer2 = new CustomerDetails("Santa", "Clause",
                "Somewhere in Highlands", "XM00AS", "0295373879");

        bookingSystem.addProvider(providerA);
        bookingSystem.addProvider(providerB);
        bookingSystem.addProvider(providerC);

        providerA.addPartner(providerB);

        // set up the pricing policies for each provider
        providerA.getPricingPolicy().setDailyRentalPrice(mountainBike, new BigDecimal(50));
        providerA.getPricingPolicy().setDailyRentalPrice(Tricycle, new BigDecimal(5));
        providerA.getPricingPolicy().setDailyRentalPrice(BMX, new BigDecimal(30));
        providerA.getPricingPolicy().setDailyRentalPrice(roadBike, new BigDecimal(45.50));

        providerB.getPricingPolicy().setDailyRentalPrice(mountainBike, new BigDecimal(40));
        providerB.getPricingPolicy().setDailyRentalPrice(Tricycle, new BigDecimal(1));
        providerB.getPricingPolicy().setDailyRentalPrice(BMX, new BigDecimal(45));
        providerB.getPricingPolicy().setDailyRentalPrice(roadBike, new BigDecimal(30));

        providerC.getPricingPolicy().setDailyRentalPrice(mountainBike, new BigDecimal(30));
        providerC.getPricingPolicy().setDailyRentalPrice(Tricycle, new BigDecimal(0.5));
        providerC.getPricingPolicy().setDailyRentalPrice(BMX, new BigDecimal(12));
        providerC.getPricingPolicy().setDailyRentalPrice(roadBike, new BigDecimal(15.75));

        // add bikes to each provider and book them to random dates
        // book bikes with providerA
        bikeA1.book(new DateRange(LocalDate.of(2019, 3, 2),
                LocalDate.of(2019, 3, 6)));
        bikeA1.book(new DateRange(LocalDate.of(2019, 3, 11),
                LocalDate.of(2019, 3, 12)));

        bikeA2.book(new DateRange(LocalDate.of(2019, 3, 5),
                LocalDate.of(2019, 3, 7)));
        bikeA2.book(new DateRange(LocalDate.of(2019, 3, 11),
                LocalDate.of(2019, 3, 17)));

        // bikeA3 isn't currently booked

        bikeA4.book(new DateRange(LocalDate.of(2019, 2, 20),
                LocalDate.of(2019, 2, 27)));
        bikeA4.book(new DateRange(LocalDate.of(2019, 3, 18),
                LocalDate.of(2019, 3, 19)));

        // book bikes with providerB
        bikeB1.book(new DateRange(LocalDate.of(2019, 3, 1),
                LocalDate.of(2019, 3, 5)));
        bikeB1.book(new DateRange(LocalDate.of(2019, 3, 11),
                LocalDate.of(2019, 3, 14)));

        bikeB2.book(new DateRange(LocalDate.of(2019, 3, 1),
                LocalDate.of(2019, 3, 2)));

        bikeB3.book(new DateRange(LocalDate.of(2019, 3, 2),
                LocalDate.of(2019, 3, 4)));
        bikeB3.book(new DateRange(LocalDate.of(2019, 3, 16),
                LocalDate.of(2019, 3, 18)));

        // book bikes with providerC
        bikeC1.book(new DateRange(LocalDate.of(2019, 12, 28),
                LocalDate.of(2020, 1, 4)));

        bikeC2.book(new DateRange(LocalDate.of(2019, 12, 25),
                LocalDate.of(2019, 12, 25)));

        bikeC3.book(new DateRange(LocalDate.of(2019, 12, 29),
                LocalDate.of(2020, 1, 12)));
        bikeC3.book(new DateRange(LocalDate.of(2019, 12, 10),
                LocalDate.of(2019, 12, 12)));

        bikeC4.book(new DateRange(LocalDate.of(2019, 12, 2),
                LocalDate.of(2019, 12, 6)));
        bikeC4.book(new DateRange(LocalDate.of(2019, 12, 10),
                LocalDate.of(2019, 12, 12)));
    }

    // use case: finding quotes//
    // test when customer wants to book bikes on a single day that is not available
    @Test
    void findQuotesforOneDay() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(Tricycle, 2);
        DateRange dates = new DateRange(LocalDate.of(2019, 12, 26),
                LocalDate.of(2019, 12, 26));
        Location area = new Location("G588YE", "Downy Street");

        // get the output the system gives
        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        // create a list of Bike and add the expected ones. List will be used to instantiate a Quote
        List<Bike> expectedBikes = new ArrayList<>();
        expectedBikes.add(bikeC1);
        expectedBikes.add(bikeC2);

        // create the expected list of Quotes
        List<Quote> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Quote(providerC, expectedBikes, dates));

        // compare the outputs using the equals function with is overridden in Quote
        assertEquals(expectedOutput, actualOutput);
    }

    // test when customer wants to book bikes on a single day that is available
    @Test
    void findQuotesDayisNotAvailable() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(Tricycle, 2);
        DateRange dates = new DateRange(LocalDate.of(2019, 12, 25),
                LocalDate.of(2019, 12, 25));
        Location area = new Location("G588YE", "Downy Street");

        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        // create the expected output which is empty as no Quotes are expected
        List<Quote> expectedOutput = new ArrayList<>();

        assertEquals(expectedOutput, actualOutput);
    }

    // test for a 3 day date range which is available for multiple providers
    @Test
    void findQuotesNormalCase() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(mountainBike, 1);
        noOfeachType.put(roadBike, 1);
        DateRange dates = new DateRange(LocalDate.of(2019, 3, 7),
                LocalDate.of(2019, 3, 10));
        Location area = new Location("EH6942", "Earth");

        // get the output the system gives
        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        // create a list of bikes for each Quote we expect to be returned
        List<Bike> expectedBikes1 = new ArrayList<>();
        expectedBikes1.add(bikeA1);
        expectedBikes1.add(bikeA4);

        List<Bike> expectedBikes2 = new ArrayList<>();
        expectedBikes2.add(bikeB1);
        expectedBikes2.add(bikeB3);

        List<Quote> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Quote(providerA, expectedBikes1, dates));
        expectedOutput.add(new Quote(providerB, expectedBikes2, dates));

        assertEquals(expectedOutput, actualOutput);
    }

    // test for a 3 day date range which is not available
    @Test
    void findQuotesNormalCaseisNotAvailable() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(mountainBike, 1);
        noOfeachType.put(roadBike, 1);
        DateRange dates = new DateRange(LocalDate.of(2019, 3, 3),
                LocalDate.of(2019, 3, 6));
        Location area = new Location("EH6942", "Earth");

        // get the output the system gives
        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        List<Quote> expectedOutput = new ArrayList<>();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void findQuotesDateWraps() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(mountainBike, 1);
        noOfeachType.put(roadBike, 1);
        DateRange dates = new DateRange(LocalDate.of(2019, 12, 28),
                LocalDate.of(2020, 1, 2));
        Location area = new Location("EH6942", "Earth");

        // get the output the system gives
        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        // create a list of bikes for each Quote we expect to be returned
        List<Bike> expectedBikes1 = new ArrayList<>();
        expectedBikes1.add(bikeA1);
        expectedBikes1.add(bikeA4);

        List<Bike> expectedBikes2 = new ArrayList<>();
        expectedBikes2.add(bikeB1);
        expectedBikes2.add(bikeB3);

        List<Quote> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Quote(providerA, expectedBikes1, dates));
        expectedOutput.add(new Quote(providerB, expectedBikes2, dates));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void findQuotesDateWrapsisNotAvailable() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(roadBike, 4);
        DateRange dates = new DateRange(LocalDate.of(2019, 12, 28),
                LocalDate.of(2020, 1, 2));
        Location area = new Location("EH6942", "Earth");

        // get the output the system gives
        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        List<Quote> expectedOutput = new ArrayList<>();

        assertEquals(expectedOutput, actualOutput);
    }

    //use case: customer books a quote //
    @Test
    void testBookQuotePickUp() {
        // create objects to initialise quote with
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bikeA1);
        bikes.add(bikeA4);
        DateRange dates = new DateRange(LocalDate.of(2019, 3, 7),
                LocalDate.of(2019, 3, 10));

        // create Quote to book
        Quote quote = new Quote(providerA, bikes, dates);

        // create values that the customer would input
        collectionMethod pickupMethod = collectionMethod.PickUp;

        // call bookQuote() and save output to actualOutput
        Booking actualOutput = bookingSystem.bookQuote(quote, pickupMethod, customer1.getFirstName(),
                customer1.getSurName(), customer1.getAddress(), customer1.getPostCode(), customer1.getPhoneNumber());

        // create the expected output
        Booking expectedOutput = new Booking(1, pickupMethod, quote, customer1);
        expectedOutput.setIsPaid(true);
        expectedOutput.getCustomer().getOrderNumbersList().add(1);

        // compare the expected output with the actual output
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testBookQuoteDelivery() {
        // create objects to initialise quote with
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bikeA1);
        bikes.add(bikeA4);
        DateRange dates = new DateRange(LocalDate.of(2019, 3, 7),
                LocalDate.of(2019, 3, 10));

        // create Quote to book
        Quote quote = new Quote(providerA, bikes, dates);

        // create values that the customer would input
        collectionMethod pickupMethod = collectionMethod.Delivery;

        // call bookQuote() and save output to actualOutput
        Booking actualOutput = bookingSystem.bookQuote(quote, pickupMethod, customer2.getFirstName(),
                customer2.getSurName(), customer2.getAddress(), customer2.getPostCode(), customer2.getPhoneNumber());

        // create the expected output
        Booking expectedOutput = new Booking(1, pickupMethod, quote, customer2);
        expectedOutput.setIsPaid(true);
        expectedOutput.getCustomer().getOrderNumbersList().add(1);

        // get the deliverable that bookQuote() adds to MockDeliveryService at the selected date
        MockDeliveryService testService = (MockDeliveryService) DeliveryServiceFactory.getDeliveryService();
        Deliverable actualDeliverable = testService.getPickupsOn(dates.getStart()).iterator().next();

        // create a deliverable that the actual one should match
        Deliverable expectedDeliverable = new DeliverableImpl(expectedOutput);

        // compare the expected deliverable to the actual one
        assertEquals(expectedDeliverable, actualDeliverable);
        // compare the expected output to the actual output
        assertEquals(expectedOutput, actualOutput);
    }

    //use case: customer returns the bike//
    //Current test is about the customer returning the bikes in their booking
    // themselves, and collectionMethod is Pickup
    @Test
    void customerReturnsBikeToProvider() {
        //Select pickUp as collectionMethod
        collectionMethod pickup = collectionMethod.PickUp;
        //Set this customer's order number for our testing purposes
        int currentCustomerOrderNumber = 1;

        //Generate a mock Quote object
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bikeA1);
        bikes.add(bikeA4);
        DateRange dates1 = new DateRange(LocalDate.of(2019, 3, 7),
                LocalDate.of(2019, 3, 10));
        DateRange dates2 = new DateRange(LocalDate.of(2019, 3, 12),
                LocalDate.of(2019, 3, 15));
        Quote quoteA1 = new Quote(providerA, bikes, dates1); //quote1 for dates1
        Quote quoteA2 = new Quote(providerA, bikes, dates2); //quote2 for dates2

        Booking bookingA1 = new Booking(1, pickup, quoteA1, customer1);
        Booking bookingA2 = new Booking(2, pickup, quoteA2, customer2);
        List<Booking> listOfBookingswithProviderA = new ArrayList<>();
        listOfBookingswithProviderA.add(bookingA1);
        listOfBookingswithProviderA.add(bookingA2);
        providerA.setBookings(listOfBookingswithProviderA);

        //Implementing test case
        //If customer hands in the bike, provider should record return.
        //bikeLocation will then be the same as the provider.
        providerA.recordReturn(currentCustomerOrderNumber);

        // test to see if the booking is removed from the Provider
        for (Booking booking : providerA.getBookings()) {
            assertNotEquals(bookingA1, booking);
        }
        for (Bike bike : bookingA1.getOrder().getBikes()) {
            // test to see if the bike location is the same as the provider's location
            assertTrue(bike.getBikeLocation().isNearTo(providerA.getShopLocation()));
            // test to see if the booked dates are removed from each bike
            for (DateRange bookedDates : bike.getBookedDates()) {
                assertNotEquals(dates1, bookedDates);
            }
        }
    }

    @Test
    void customerReturnsBikeToPartneredProvider() {
        //Select pickUp as collectionMethod
        collectionMethod pickup = collectionMethod.PickUp;
        //Set this customer's order number for our testing purposes
        //Assuming that Customer1 is the customer returning the bikes
        int currentCustomerOrderNumber = 1;

        //Generate a mock Quote object
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bikeA1);
        bikes.add(bikeA4);
        DateRange dates1 = new DateRange(LocalDate.of(2019, 3, 7),
                LocalDate.of(2019, 3, 10));
        DateRange dates2 = new DateRange(LocalDate.of(2019, 3, 12),
                LocalDate.of(2019, 3, 15));
        Quote quoteA1 = new Quote(providerA, bikes, dates1); //quote1 for dates1
        Quote quoteA2 = new Quote(providerA, bikes, dates2); //quote2 for dates2

        Booking bookingA1 = new Booking(1, pickup, quoteA1, customer1);
        Booking bookingA2 = new Booking(2, pickup, quoteA2, customer2);
        List<Booking> listOfBookingsWithProviderA = new ArrayList<>();
        listOfBookingsWithProviderA.add(bookingA1);
        listOfBookingsWithProviderA.add(bookingA2);

        //we will now set all made bookings to a provider for testing purposes
        providerA.setBookings(listOfBookingsWithProviderA);
        //Since bikes are with customer, for testing purposes, we will setBikeStatuses to be with Customer
        bookingA1.setBikesStatus(bikeStatuses.withCustomer);

        //Customer is now with the partner and has returned the bikes in the booking to the partner.
        //partnered provider will now attempt to get it back to original provider and records the return
        providerB.recordReturn(bookingA1.getOrderNumber());
        //First ensure that bikeStatuses has changed to be inTransit instead of with customer
        //Deliverable bikesInBookingtoBeDelivered = new DeliverableImpl(bookingA1);

        MockDeliveryService deliveryService = (MockDeliveryService) DeliveryServiceFactory.getDeliveryService();
        //deliveryService.scheduleDelivery(bikesInBookingtoBeDelivered,
        //        providerB.getShopLocation(), providerA.getShopLocation(), dates1.getEnd()); //dates1 for customer1 Booking

        assertEquals(bookingA1.getBikesStatus(), bikeStatuses.withCustomer);
        deliveryService.carryOutPickups(dates1.getEnd());
        assertEquals(bookingA1.getBikesStatus(), bikeStatuses.inTransit);
        deliveryService.carryOutDropoffs();

        List<Bike> deliveredBikes = bookingA1.getOrder().getBikes();
        for (Bike bike : deliveredBikes) {
            assertTrue(bike.getBikeLocation().isNearTo(providerA.getShopLocation()));
            // test to see if the booked dates are removed from each bike
            for (DateRange bookedDates : bike.getBookedDates()) {
                assertNotEquals(dates1, bookedDates);
            }
        }

        //check if bikes are now with Provider once we carryOutDropoffs
        //this means that the driver should have successfully dropped off the bikes to the provider
        assertEquals(bookingA1.getBikesStatus(), bikeStatuses.withProvider);

        //If customer hands in the bike, provider should record return
        //Make sure that the current customer bookings with providerA is destroyed
        //and is no longer in the system
        providerA.recordReturn(currentCustomerOrderNumber);
        for (Booking booking : providerA.getBookings()) {
            assertNotEquals(bookingA1, booking);
        }
    }
}
