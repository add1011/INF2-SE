package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Quote {
    // initialize attributes
    private Provider provider;
    private List<Bike> bikes;
    private DateRange selectedDates;
    private Location providerLocation;
    private BigDecimal totalPrice;
    private BigDecimal totalDeposit;

    public Quote(Provider provider, List<Bike> bikes, DateRange selectedDates,
                 Location providerLocation) {
        this.provider = provider;
        this.bikes = bikes;
        this.selectedDates = selectedDates;
        this.providerLocation = providerLocation;
        this.totalPrice = new BigDecimal("0");
        this.totalDeposit = new BigDecimal("0");
    }

    // calculate the total price using the provider's pricing policy
    private void calcTotalPrice() {
        this.totalPrice = provider.getPricingPolicy().calculatePrice(bikes, selectedDates);
    }

    // calculate the sum of deposits of each bike
    private void calcTotalDeposit() {
        BigDecimal totalDeposit = new BigDecimal(0);
        for (Bike bike : this.bikes) {
            totalDeposit = totalDeposit.add(bike.getDepositAmount());
        }
        this.totalDeposit = totalDeposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Quote q = (Quote) o;

        return Objects.equals(bikes, q.bikes) &&
                Objects.equals(provider, q.provider) &&
                Objects.equals(providerLocation, q.providerLocation) &&
                Objects.equals(totalPrice, q.totalPrice) &&
                Objects.equals(totalDeposit, q.totalDeposit);
    }

    // Getters and Setters
    public Provider getProvider() { return provider; }

    public void setProvider(Provider provider) { this.provider = provider; }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List bikes) {
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
