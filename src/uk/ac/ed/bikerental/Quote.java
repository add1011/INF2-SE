package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Quote {
    // initialize attributes
    private Bike[] bikes;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private Location providerLocation;
    private BigDecimal totalPrice;
    private BigDecimal totalDeposit;

    public Quote(Bike[] bikes, LocalDate pickupDate, LocalDate returnDate,
                 Location providerLocation) {
        this.bikes = bikes;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.providerLocation = providerLocation;
        this.totalPrice = new BigDecimal("0");
        this.totalDeposit = new BigDecimal("0");
    }

    // TODO: needs to be implemented
    private BigDecimal calcTotalPrice() {
        return null;
    }

    // TODO: needs to be implemented
    private BigDecimal calcTotalDeposit() {
        return null;
    }

    // getters and setters
    public Bike[] getBikes() {
        return bikes;
    }

    public void setBikes(Bike[] bikes) {
        this.bikes = bikes;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Location getProviderLocation() {
        return providerLocation;
    }

    public void setProviderLocation(Location providerLocation) {
        this.providerLocation = providerLocation;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(BigDecimal totalDeposit) {
        this.totalDeposit = totalDeposit;
    }
}
