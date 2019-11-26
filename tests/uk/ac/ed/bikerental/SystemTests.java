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
            new BigDecimal(0.8));
    private Provider providerB = new Provider("The Bike Station", EdinburghB, new BigDecimal(34.7),
            new BigDecimal(1.1));
    private Provider providerC = new Provider("Date Bike Place", GlasgowA, new BigDecimal(12.50),
            new BigDecimal(1));

    Bike bikeA1 = providerA.addBike(mountainBike);
    Bike bikeA2 = providerA.addBike(mountainBike);
    Bike bikeA3 = providerA.addBike(Tricycle);
    Bike bikeA4 = providerA.addBike(roadBike);

    Bike bikeB1 = providerB.addBike(mountainBike);
    Bike bikeB2 = providerB.addBike(BMX);
    Bike bikeB3 = providerB.addBike(roadBike);

    Bike bikeC1 = providerC.addBike(Tricycle);
    Bike bikeC2 = providerC.addBike(Tricycle);
    Bike bikeC3 = providerC.addBike(roadBike);
    Bike bikeC4 = providerC.addBike(roadBike);


    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();

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
        bikeA1.book(new DateRange(LocalDate.of(2019,3,10),
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
    @Test
    void findQuotesTest1() {
        Map<String, Integer> noOfeachType = new HashMap<>();


        Collection<Bike> outputBikes = new ArrayList<>();
        Collection<Quote> expectedOutput = new ArrayList<>();
        DateRange outputDates = new DateRange(LocalDate.of(2019,3,8),
                LocalDate.of(2019,3,9));

        expectedOutput.add(new Quote(providerA, outputBikes, outputDates, EdinburghA));

        DateRange dates = new DateRange(LocalDate.of(2019,3,7),
                LocalDate.of(2019,3,8  ));
        Location area = new Location("EH69 8YE", "Downy Street");
        Collection<Quote> actualOutput = new BookingSystem(noOfeachType, dates, area);

        assertEquals(expectedOutput, actualOutput);
    }
}
