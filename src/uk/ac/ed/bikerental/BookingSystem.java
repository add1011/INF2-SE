package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookingSystem {
    //maintains a list of all the existing providers in our system
    private List<Provider> listOfExistingProviders = new ArrayList<>();
    private int orderNumberTrack =0;



    public void addProvider(Provider prov){
        listOfExistingProviders.add(prov);
    }

    public void removeProvider(Provider prov){
        listOfExistingProviders.remove(prov);
    }

    /**
     * Helper function for getQuotes() -> returns providers in that location
     * In case we have more providers in the same location, return a list of providers
     */
    private List<Provider> providerInLocation(Location location){
        List<Provider> matchingProviders = new ArrayList<>();
        for (Provider prov : listOfExistingProviders){
            if (prov.getShopLocation().isNearTo(location)){
                matchingProviders.add(prov);
            }
        }
        return matchingProviders;
    }

    /*
     *Helper function:
     * Current implementation uses integer to generate orderNumber;
     *Since we might run out of numbers, future implementations might change ordernumbers
     *to include Alphanumeric characters. This was to detailed-level, so we assumed we can use integer for the purposes
     * of this coursework
     */
    private int generateOrderNumber(){
        //As a small checker, we added this to avoid huge number problems. The system notifies the user
        // that we are running
        if(orderNumberTrack >= 99999999){
            throw new IndexOutOfBoundsException("Exceeded amount of order numbers!");
        }
            orderNumberTrack += 1;
        return orderNumberTrack;
    }

    private List<Bike> areBikesAvailable(Map<BikeType, Integer> noOfTypes, DateRange wantedDates, Provider provider) {
        Map<BikeType, Integer> wantedAmount = new HashMap<>();
        for (Map.Entry entry : noOfTypes.entrySet()) {
            wantedAmount.put((BikeType) entry.getKey(), 0);
        }

        List<Bike> bikes = provider.getBikes();
        List<Bike> availableBikes = new ArrayList<>();
        // for each bike the provider owns
        for (Bike bike : bikes) {
            // if the bike matches the type wanted, the number needed is not fulfilled and the bike has no booked dates
            if (bike.getBookedDates().size() == 0 && wantedAmount.containsKey(bike.getType()) &&
                    wantedAmount.get(bike.getType()) != noOfTypes.get(bike.getType())) {
                // increment the amount of the type that are available by 1 and add the bike
                Integer amountNeeded = wantedAmount.get(bike.getType());
                wantedAmount.put(bike.getType(), amountNeeded+1);
                availableBikes.add(bike);
            } else {
                Boolean overlaps = false;
                // if the selected dates do not overlap with any of the booked dates
                for (DateRange bookedDate : bike.getBookedDates()) {
                    if (bookedDate.overlaps(wantedDates)) {
                        overlaps = true;
                        break;
                    }
                }
                // and if the bike is the type wanted and the number needed of that type is not fulfilled
                if (wantedAmount.containsKey(bike.getType()) &&
                        wantedAmount.get(bike.getType()) != noOfTypes.get(bike.getType()) && !overlaps) {
                    // increment the amount of the type that are available by 1 and add the bike
                    Integer amountNeeded = wantedAmount.get(bike.getType());
                    wantedAmount.put(bike.getType(), amountNeeded+1);
                    availableBikes.add(bike);;
                }
            }
        }
        // if the available bikes fulfill the requested amount of types, return the list of bikes else return null
        for (Map.Entry<BikeType, Integer> entry : wantedAmount.entrySet()) {
            if (entry.getValue() != noOfTypes.get(entry.getKey())) {
                return null;
            }
        }
        return availableBikes;
    }

    /*
     * noOfTypes denotes how much of each bikeType does the customer need
     * as they might require 2 bikes of a particular BikeType
     */
    public List<Quote> getQuotes(Map<BikeType,Integer> noOfTypes, DateRange selectedDates, Location location){
       List<Provider> matchingProviders = providerInLocation(location);
       List<Quote> quotes = new ArrayList<>();

       //adds bikes that are available on the selected dates from the matching provides to the list bikesFromProviders
       for (Provider provider : matchingProviders){
           List<Bike> availableBikes = areBikesAvailable(noOfTypes, selectedDates, provider);
           if (availableBikes != null) {
               quotes.add(new Quote(provider,availableBikes,selectedDates, provider.getShopLocation()));
           }
       }
       return quotes;
    }

    public Booking bookQuote(Quote quote, collectionMethod pickupMethod,
                     String firstName, String surName, String address,
                     String postcode, int phoneNumber ) {
        // create a customerDetails object
        CustomerDetails customer = new CustomerDetails(firstName,surName,address,postcode,phoneNumber);

        int newOrderNumber = generateOrderNumber();
        List<Bike>listOfBikes = quote.getBikes();
        // adds the booking dates to all the bikes in the order
        for(Bike bike : listOfBikes){
            bike.book(quote.getSelectedDates());
        }
        Booking booking = new Booking(newOrderNumber, pickupMethod, quote, customer);
        booking.checkout();

        if (pickupMethod == collectionMethod.PickUp) {
            Location deliveryTarget = new Location(customer.getAddress(), customer.getPostCode());
            DeliveryServiceFactory.getDeliveryService().scheduleDelivery(new DeliverableImpl(),
                    quote.getProviderLocation(), deliveryTarget, quote.getSelectedDates().getStart());
        }

        return booking;
    }


}
