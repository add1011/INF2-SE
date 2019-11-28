package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Bike {
    // attributes
    private Provider provider;
    private BikeType type;
    private NormalValuationPolicy depositAmount;
    private Location bikeLocation;
    private ArrayList<DateRange> bookedDates;

    // constructors //
    // constructor to allow Bike to be initialized without provider for testing
    public Bike(BigDecimal depositAmount, Location bikeLocation,
                ArrayList<DateRange> bookedDates, BikeType type) {
        this.depositAmount = new NormalValuationPolicy();
        this.depositAmount.setDepositAmount(depositAmount);
        this.bikeLocation = bikeLocation;
        this.bookedDates = bookedDates;
        this.type = type;
    }

    // actual Bike constructor used in practice
    public Bike(Provider provider, Location bikeLocation, BikeType type) {
        this.provider = provider;
        this.bikeLocation = bikeLocation;
        this.bookedDates = new ArrayList<>();
        this.type = type;
        this.depositAmount = new NormalValuationPolicy();
        calcDepositAmount();
    }

    // methods //
    public void calcDepositAmount() {
        BigDecimal deposit = new BigDecimal(0);
        deposit = this.type.getReplacementValue();
        deposit = deposit.multiply(this.provider.getDepositRate());
        this.depositAmount.setDepositAmount(deposit.setScale(2, RoundingMode.HALF_UP));
    }

    //adds a new booking date to bike - checker for overlap
    public void book(DateRange bookDates) {
        this.bookedDates.add(bookDates);
        /**for(DateRange date: this.bookedDates){
            if (date.overlaps(bookDates)){
                //System.out.println("Current bike is booked for this date: " + bookDates.toString());
            }
            else{
                bookedDates.add(bookDates);
            }
        }**/
    }

    // getters and setters
    public BikeType getType() { return type; }

    public void setType(BikeType type) {
        this.type = type;
    }

    public BigDecimal getDepositAmount() { return this.depositAmount.getDepositAmount(); }

    public void setDepositAmount(NormalValuationPolicy depositAmount) {
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
}