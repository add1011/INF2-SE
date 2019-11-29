package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Quote {
    // attributes //
    private Provider provider;
    private List<Bike> bikes;
    private DateRange selectedDates;
    private Location providerLocation;
    private BigDecimal totalPrice;
    private BigDecimal totalDeposit;

    // constructors //
    public Quote(Provider provider, List<Bike> bikes, DateRange selectedDates,
                 Location providerLocation) {
        this.provider = provider;
        this.bikes = bikes;
        this.selectedDates = selectedDates;
        this.providerLocation = providerLocation;
        calcTotalPrice();
        calcTotalDeposit();
    }

    // methods //
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

    // override the equals method for this class to use in testing
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

    // getters and setters //
    public Provider getProvider() { return provider; }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List bikes) {
        this.bikes = bikes;
    }

    public DateRange getSelectedDates() { return selectedDates; }

    public Location getProviderLocation() {
        return providerLocation;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getTotalDeposit() {
        return totalDeposit;
    }
}
