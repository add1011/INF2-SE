package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Provider {
    private DiscountPolicy pricingPolicy;
    private int providerID;
    private String providerName;
    private Location shopLocation;
    private BigDecimal dailyRentalPrice;
    private BigDecimal depositRate;
    private Collection<Provider> partners;

    public Provider(int providerID, String providerName, Location shopLocation, BigDecimal dailyRentalPrice,
                    BigDecimal depositRate, Collection<Provider> partners) {
        this.pricingPolicy = new DiscountPolicy();
        this.providerID = providerID;
        this.providerName = providerName;
        this.shopLocation = shopLocation;
        this.dailyRentalPrice = dailyRentalPrice;
        this.depositRate = depositRate;
        this.partners = partners;
    }

    // Getters and Setters
    public int getProviderID() {
        return providerID;
    }

    /**public void setProviderID(int providerID) {
        this.providerID = providerID;
    } not needed**/

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

    public void setPartners(Collection<Provider> partners) {
        this.partners = partners;
    }

    public Boolean compareLocation(Location location) {
        return location == this.shopLocation;
    }

    public void recordReturn(int orderNumber) {

    }

    private void notifyProvider(Booking booking) {

    }
}
