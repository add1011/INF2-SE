package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Quote {
    // initialize attributes
    private Bike[] bikes;
    private DateRange selectedDates;
    private Location providerLocation;
    private BigDecimal totalPrice;
    private BigDecimal totalDeposit;

    public Quote(Bike[] bikes, DateRange selectedDates,
                 Location providerLocation) {
        this.bikes = bikes;
        this.selectedDates = selectedDates;
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

    // Getters and Setters
    public Bike[] getBikes() {
        return bikes;
    }

    public void setBikes(Bike[] bikes) {
        this.bikes = bikes;
    }

    public DateRange getSelectedDates() { return selectedDates; }

    public void setSelectedDates(DateRange selectedDates) { this.selectedDates = selectedDates; }

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
