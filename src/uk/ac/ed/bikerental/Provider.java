package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Provider {
    private DiscountPolicy pricingPolicy;
    private String providerName;
    private Location shopLocation;
    private BigDecimal dailyRentalPrice;
    private BigDecimal depositRate;
    private Collection<Provider> partners;

    public Provider(String providerName, Location shopLocation, BigDecimal dailyRentalPrice,
                    BigDecimal depositRate) {
        this.pricingPolicy = new DiscountPolicy();
        this.providerName = providerName;
        this.shopLocation = shopLocation;
        this.dailyRentalPrice = dailyRentalPrice;
        this.depositRate = depositRate;
        this.partners = new ArrayList<>();
    }

    public Bike addBike(BigDecimal depositAmount, Location bikeLocation, Integer providerID,
                        ArrayList<DateRange> bookedDates, BikeType type) {
        return new Bike(this, bikeLocation, bookedDates, type);
    }

    public void addPartner(Provider partner) {
        this.setPartner(partner);
        partner.setPartner(this);
    }
    // Getters and Setters
    public DiscountPolicy getPricingPolicy() { return pricingPolicy; }

    public void setPricingPolicy(DiscountPolicy pricingPolicy) { this.pricingPolicy = pricingPolicy; }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Location getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(Location shopLocation) {
        this.shopLocation = shopLocation;
    }

    public BigDecimal getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public void setDailyRentalPrice(BigDecimal dailyRentalPrice) {
        this.dailyRentalPrice = dailyRentalPrice;
    }

    public BigDecimal getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public Collection<Provider> getPartners() {
        return partners;
    }

    public void setPartner(Provider partner) {
        this.partners.add(partner);
    }

    public Boolean compareLocation(Location location) {
        return location == this.shopLocation;
    }

    public void recordReturn(int orderNumber) {

    }

    private void notifyProvider(Booking booking) {

    }
}
