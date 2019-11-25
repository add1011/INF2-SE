package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bike {
    // initialize attributes
    private BigDecimal depositAmount;
    private Location bikeLocation;
    private Integer ProviderID;
    private ArrayList<DateRange> bookedDates;
    private BikeType type;

    // constructor is temporary and likely will have to be changed
    public Bike(BigDecimal depositAmount, Location bikeLocation, Integer providerID, ArrayList<DateRange> bookedDates, BikeType type) {
        this.depositAmount = depositAmount;
        this.bikeLocation = bikeLocation;
        ProviderID = providerID;
        this.bookedDates = bookedDates;
        this.type = type;
    }

    public BikeType getType() {
        return type;

    }

    // getters and setters
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Location getBikeLocation() {
        return bikeLocation;
    }

    public void setBikeLocation(Location bikeLocation) {
        this.bikeLocation = bikeLocation;
    }

    public Integer getProviderID() {
        return ProviderID;
    }

    public void setProviderID(Integer providerID) {
        ProviderID = providerID;
    }

    public ArrayList<DateRange> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(ArrayList<DateRange> bookedDates) {
        this.bookedDates = bookedDates;
    }
}