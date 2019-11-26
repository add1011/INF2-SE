package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BookingSystem {
    //maintains a list of all the existing providers in our system
    private ArrayList<Provider> listOfExistingProviders;

    public BookingSystem(){
        listOfExistingProviders = new ArrayList<>();
    }

    public void addProvider(Provider prov){
        listOfExistingProviders.add(prov);
    }

    public void removeProvider(Provider prov){
        listOfExistingProviders.remove(prov);
    }

    /**
     * Helper function for getQuotes() -> returns providers in that location
     * In case we have more providers in the same location, return an array of providers
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

    public List<Quote> getQuotes(Map<BikeType,Integer> noOfTypes, DateRange selectedDates, Location location){
       List<Provider> matchingProviders = providerInLocation(location);
       return null;
    }

    public Booking bookQuote(Quote quote, collectionMethod collectMethod,
                     String firstName, String surName, String address,
                     String postcode, int phoneNumber ) {
        Customer customer = new Customer(firstName,surName,address,postcode,phoneNumber);
        return null;
    }


}
