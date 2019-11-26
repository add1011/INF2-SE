package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class BookingSystem {
    //Map<String,Integer>noOfTypes : the amount of each bikes
    ArrayList<Provider> listOfExistingProviders;
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
    private ArrayList<Provider> providerInLocation(Location location){
        ArrayList matchingProviders = new ArrayList<>();
        for (Provider prov:listOfExistingProviders){
            if (prov.getShopLocation().isNearTo(location)){
                matchingProviders.add(prov);
            }
        }
        return matchingProviders;
    }

    public Collection<Quote> getQuotes(Map<BikeType,Integer> noOfTypes, DateRange selectedDates, Location location){
       ArrayList<Provider> matchingProviders = providerInLocation(location);
       return null;
    }
}
