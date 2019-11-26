package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bike {
    // initialize attributes
    private Provider provider;
    private BikeType type;
    private BigDecimal depositAmount;
    private Location bikeLocation;
    private ArrayList<DateRange> bookedDates;


    // constructor to allow Bike to be initialized without provider for testing
    public Bike(BigDecimal depositAmount, Location bikeLocation,
                ArrayList<DateRange> bookedDates, BikeType type) {
        this.depositAmount = depositAmount;
        this.bikeLocation = bikeLocation;
        this.bookedDates = bookedDates;
        this.type = type;
    }

    // actual Bike constructor
    public Bike(Provider provider, Location bikeLocation, BikeType type) {
        this.provider = provider;
        this.bikeLocation = bikeLocation;
        this.bookedDates = new ArrayList<>();
        this.type = type;
        calcDepositAmount();
    }

    public void calcDepositAmount() {
        BigDecimal deposit = new BigDecimal(0);
        deposit = this.type.getReplacementValue();
        deposit = deposit.multiply(this.provider.getDepositRate());
        this.depositAmount = deposit;
    }

    public void book(DateRange bookDates) {
        bookedDates.add(bookDates);
    }

    // getters and setters
    public BikeType getType() { return type; }

    public void setType(BikeType type) {
        this.type = type;
    }

    public BigDecimal getDepositAmount() { return depositAmount; }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Location getBikeLocation() {
        return bikeLocation;
    }

    public void setBikeLocation(Location bikeLocation) {
        this.bikeLocation = bikeLocation;
    }

    public ArrayList<DateRange> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(ArrayList<DateRange> bookedDates) {
        this.bookedDates = bookedDates;
    }
}