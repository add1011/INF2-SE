package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class SystemTests {
    // You can add attributes here
    private BookingSystem bookingSystem = new BookingSystem();
    // add locations for providers
    private Location EdinburghA = new Location("EH12NG", "CastleHill");
    private Location EdinburghB = new Location("EH91UU", "Causewayside");
    private Location GlasgowA = new Location("G58SG", "Houston Place");

    // create bike types to use
    private BikeType mountainBike = new BikeType("Mountain Bike", new BigDecimal(205.4));
    private BikeType Tricycle = new BikeType("Tricycle", new BigDecimal(33.3));
    private BikeType BMX = new BikeType("BMX", new BigDecimal(79));
    private BikeType roadBike = new BikeType("Road Bike", new BigDecimal(100));

    // instantiate providers
    private Provider providerA = new Provider("Bikes'R'Us", EdinburghA, new BigDecimal(50),
            new BigDecimal(0.8), new DiscountPolicy());
    private Provider providerB = new Provider("The Bike Station", EdinburghB, new BigDecimal(34.7),
            new BigDecimal(1.1), new DiscountPolicy());
    private Provider providerC = new Provider("Dat Bike Place", GlasgowA, new BigDecimal(12.50),
            new BigDecimal(1), new NormalPricingPolicy());

    //addBike method adds bike to the list of bikes stored with a provider and returns that bike
    private Bike bikeA1 = providerA.addBike(mountainBike);
    private Bike bikeA2 = providerA.addBike(mountainBike);
    private Bike bikeA3 = providerA.addBike(Tricycle);
    private Bike bikeA4 = providerA.addBike(roadBike);

    private Bike bikeB1 = providerB.addBike(mountainBike);
    private Bike bikeB2 = providerB.addBike(BMX);
    private Bike bikeB3 = providerB.addBike(roadBike);

    private Bike bikeC1 = providerC.addBike(Tricycle);
    private Bike bikeC2 = providerC.addBike(Tricycle);
    private Bike bikeC3 = providerC.addBike(roadBike);
    private Bike bikeC4 = providerC.addBike(roadBike);

    CustomerDetails customer1 = new CustomerDetails("Imaginary", "John", "Somewhere in Scotland", "EH10BB", "02385738743");
    CustomerDetails customer2 = new CustomerDetails("Santa", "Clause", "Somewhere in Highlands", "XM00AS", "02385738743");


    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();

        bookingSystem.addProvider(providerA);
        bookingSystem.addProvider(providerB);
        bookingSystem.addProvider(providerC);


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
        bikeA1.book(new DateRange(LocalDate.of(2019,3,2),
                LocalDate.of(2019,3,6  )));
        bikeA1.book(new DateRange(LocalDate.of(2019,3,11),
                LocalDate.of(2019,3,12  )));

        bikeA2.book(new DateRange(LocalDate.of(2019,3,5),
                LocalDate.of(2019,3,7  )));
        bikeA2.book(new DateRange(LocalDate.of(2019,3,11),
                LocalDate.of(2019,3,17  )));

        // bikeA3 isn't currently booked

        bikeA4.book(new DateRange(LocalDate.of(2019,2,20),
                LocalDate.of(2019,2,27  )));
        bikeA4.book(new DateRange(LocalDate.of(2019,3,18),
                LocalDate.of(2019,3,19  )));

        // book bikes with providerB
        bikeB1.book(new DateRange(LocalDate.of(2019,3,1),
                LocalDate.of(2019,3,5  )));
        bikeB1.book(new DateRange(LocalDate.of(2019,3,11),
                LocalDate.of(2019,3,14  )));

        bikeB2.book(new DateRange(LocalDate.of(2019,3,1),
                LocalDate.of(2019,3,2  )));

        bikeB3.book(new DateRange(LocalDate.of(2019,3,2),
                LocalDate.of(2019,3,4  )));
        bikeB3.book(new DateRange(LocalDate.of(2019,3,16),
                LocalDate.of(2019,3,18  )));

        // book bikes with providerC
        bikeC1.book(new DateRange(LocalDate.of(2019,12,28),
                LocalDate.of(2020,1,4  )));

        bikeC2.book(new DateRange(LocalDate.of(2019,12,25),
                LocalDate.of(2019,12,25  )));

        bikeC3.book(new DateRange(LocalDate.of(2020,1,5),
                LocalDate.of(2020,1,12  )));
        bikeC3.book(new DateRange(LocalDate.of(2019,12,10),
                LocalDate.of(2019,12,12  )));

        bikeC4.book(new DateRange(LocalDate.of(2019,12,2),
                LocalDate.of(2019,12,6  )));
        bikeC4.book(new DateRange(LocalDate.of(2019,12,10),
                LocalDate.of(2019,12,12  )));
    }
    
    // TODO: Write system tests covering the three main use cases
    // use case: finding quotes
    // test when customer wants to book bikes on a single day that is not available
    @Test
    void findQuotesforOneDay() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(Tricycle, 2);
        DateRange dates = new DateRange(LocalDate.of(2019,12,26),
                LocalDate.of(2019,12,26  ));
        Location area = new Location("G588YE", "Downy Street");

        // get the output the system gives
        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        // create a list of Bike and add the expected ones. List will be used to instantiate a Quote
        List<Bike> expectedBikes = new ArrayList<>();
        expectedBikes.add(bikeC1);
        expectedBikes.add(bikeC2);

        // create the expected list of Quotes
        List<Quote> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Quote(providerC, expectedBikes, dates, GlasgowA));

        // compare the outputs using the equals function with is overridden in Quote
        assertEquals(expectedOutput, actualOutput);
    }

    // test when customer wants to book bikes on a single day that is available
    @Test
    void findQuotesDayisBooked() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(Tricycle, 2);
        DateRange dates = new DateRange(LocalDate.of(2019,12,25),
            LocalDate.of(2019,12,25  ));
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
        DateRange dates = new DateRange(LocalDate.of(2019,3,7),
                LocalDate.of(2019,3,10  ));
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
        expectedOutput.add(new Quote(providerA, expectedBikes1, dates, EdinburghA));
        expectedOutput.add(new Quote(providerB, expectedBikes2, dates, EdinburghB));

        assertEquals(expectedOutput, actualOutput);
    }

    // test for a 3 day date range which is not available
    @Test
    void findQuotesNormalCaseisBooked() {
        // create inputs that the user would select
        Map<BikeType, Integer> noOfeachType = new HashMap<>();
        noOfeachType.put(mountainBike, 1);
        noOfeachType.put(roadBike, 1);
        DateRange dates = new DateRange(LocalDate.of(2019,3,3),
                LocalDate.of(2019,3,6  ));
        Location area = new Location("EH6942", "Earth");

        // get the output the system gives
        List<Quote> actualOutput = bookingSystem.getQuotes(noOfeachType, dates, area);

        List<Quote> expectedOutput = new ArrayList<>();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testBookQuote() {
        // create objects to initialise quote with
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bikeA1);
        bikes.add(bikeA4);
        DateRange dates = new DateRange(LocalDate.of(2019,3,7),
                LocalDate.of(2019,3,10  ));

        Quote quote = new Quote(providerA, bikes, dates, EdinburghA);

        collectionMethod pickupMethod = collectionMethod.PickUp;
        String firstName = "Robbie";
        String surName = "Beedy";
        String address = "5 dat street";
        String postcode = "EH588UH";
        String phoneNumber = "03534624976";

        Booking actualOutput = bookingSystem.bookQuote(quote,pickupMethod, firstName,
                surName, address, postcode, phoneNumber);

        CustomerDetails customer = new CustomerDetails(firstName, surName, address, postcode, phoneNumber);
        Booking expectedOutput = new Booking(1, pickupMethod, quote, customer);
        expectedOutput.setIsPaid(true);
        expectedOutput.getCustomer().getOrderNumbersList().add(1);

        assertEquals(expectedOutput, actualOutput);
    }

    //Current test is about the customer returning one bike themselves, so collectionMethod is Pickup
    @Test
    void customerReturnsBikeToProvider(){
        //Select pickUp as collectionMethod
        collectionMethod pickup = collectionMethod.PickUp;
        //Set this customer's order number for our testing purposes
        int currentCustomerOrderNumber= 1;

        //Generate a mock Quote object
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bikeA1);
        bikes.add(bikeA4);
        DateRange dates1 = new DateRange(LocalDate.of(2019,3,7),
                LocalDate.of(2019,3,10  ));
        DateRange dates2 = new DateRange(LocalDate.of(2019,3,12),
                LocalDate.of(2019,3,15  ));
        Quote quoteA1 = new Quote(providerA, bikes, dates1, EdinburghA); //quote1 for dates1
        Quote quoteA2 = new Quote(providerA, bikes, dates2, EdinburghA); //quote2 for dates2

        Booking bookingA1 = new Booking(1,pickup,quoteA1, customer1);
        Booking bookingA2 = new Booking(2,pickup,quoteA2, customer2);
        List<Booking> listOfBookingswithProviderA = new ArrayList<>();
        listOfBookingswithProviderA.add(bookingA1);
        listOfBookingswithProviderA.add(bookingA2);
        providerA.setBookings(listOfBookingswithProviderA);

        //Implementing test case
        //If customer hands in the bike, provider should record return
        providerA.recordReturn(currentCustomerOrderNumber);
        for( Booking booking:providerA.getBookings()){
            assertNotEquals(bookingA1, booking);
        }
    }

    //Current test is about the customer returning several bikes themselves, so collectionMethod is Pickup
    @Test
    void customerReturnsSeveralBikesToProvider(){
        //Select pickUp as collectionMethod
        collectionMethod pickup = collectionMethod.PickUp;

        //Generate a mock Quote object
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bikeA1);
        bikes.add(bikeA4);
        DateRange dates1 = new DateRange(LocalDate.of(2019,3,7),
                LocalDate.of(2019,3,10  ));
        DateRange dates2 = new DateRange(LocalDate.of(2019,3,12),
                LocalDate.of(2019,3,15  ));
        DateRange dates3 = new DateRange(LocalDate.of(2019,3,19),
                LocalDate.of(2019,3,22  ));
        Quote quoteA1 = new Quote(providerA, bikes, dates1, EdinburghA); //quote1 for dates1
        Quote quoteA3 = new Quote(providerA, bikes, dates3, EdinburghA); //quote3 for dates3

        //bookings made by customer1
        Booking bookingA1 = new Booking(1,pickup,quoteA1, customer1);
        Booking bookingA2 = new Booking(2,pickup,quoteA1, customer1);
        List<Booking>customer1Bookings = new ArrayList<>();
        customer1Bookings.add(bookingA1);
        customer1Bookings.add(bookingA2);

        Booking bookingA3 = new Booking(3,pickup,quoteA3,customer2);
        List<Booking> listOfBookingswithProviderA = new ArrayList<>();
        listOfBookingswithProviderA.add(bookingA1);
        listOfBookingswithProviderA.add(bookingA2);
        listOfBookingswithProviderA.add(bookingA3);
        //we will now set all made bookings to a provider for testing purposes
        providerA.setBookings(listOfBookingswithProviderA);

        //Implementing test case
        //If customer hands in the bike, provider should record return
        for(Booking EachCustomer1booking : customer1Bookings) {
            providerA.recordReturn(EachCustomer1booking.getOrderNumber());
        }
        for( Booking booking:providerA.getBookings()){
            assertNotEquals(bookingA1, booking);
        }
    }
    @Test
    void customerReturnsBikeToPartneredProvider(){
        
    }
}
