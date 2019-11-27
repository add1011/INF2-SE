package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Provider {
    private List<Booking> bookings = new ArrayList<>();
    private List<Bike> bikes = new ArrayList<>();
    private PricingPolicy pricingPolicy;
    private String providerName;
    private Location shopLocation;
    private BigDecimal dailyRentalPrice;
    private BigDecimal depositRate;
    private List<Provider> partners;

    // constructors //
    public Provider(String providerName, Location shopLocation, BigDecimal dailyRentalPrice,
                    BigDecimal depositRate, PricingPolicy policy) {
        this.providerName = providerName;
        this.shopLocation = shopLocation;
        this.dailyRentalPrice = dailyRentalPrice;
        this.depositRate = depositRate;
        this.partners = new ArrayList<>();
        this.pricingPolicy = policy;
    }

    // methods //
    public Bike addBike(BikeType type) {
        Bike bike = new Bike(this, this.getShopLocation(), type);
        this.bikes.add(bike);
        return bike;
    }

    // adds the partners to each other's partners attribute
    public void addPartner(Provider partner) {
        this.getPartners().add(partner);
        partner.getPartners().add(partner);
    }

    public void removePartner(Provider partner) {
        this.getPartners().remove(partner);
        partner.getPartners().remove(this);
    }

    public void recordReturn(int orderNumber) {
        for (int i = 0; i< this.getBookings().size(); i++) {
            if (this.getBookings().get(i).getOrderNumber() == orderNumber) {
                Booking booking = this.getBookings().get(i);
                if (booking.getOrder().getProviderLocation() != this.getShopLocation()) {
                    notifyProvider(booking);
                    DeliveryServiceFactory.getDeliveryService().scheduleDelivery(new DeliverableImpl(booking),
                            this.getShopLocation(), booking.getOrder().getProviderLocation(),
                            booking.getOrder().getSelectedDates().getEnd());
                }
                for (Bike bike : booking.getOrder().getBikes()) {
                    bike.setBikeLocation(this.getShopLocation());
                }
                this.getBookings().remove(i);
                break;
            }
        }
    }

    private void notifyProvider(Booking booking) {
        System.out.println(booking.getOrder().getProvider().getProviderName()
                + " has been notified of the bike return.");
    }

    // Getters and Setters //
    public List<Booking> getBookings() { return bookings; }

    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }

    public PricingPolicy getPricingPolicy() { return pricingPolicy; }

    public void setPolicyToDiscount(DiscountPolicy pricingPolicy) { this.pricingPolicy = pricingPolicy; }

    public void setPolicyToNormal(NormalPricingPolicy pricingPolicy) { this.pricingPolicy = pricingPolicy;}

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

    public List<Provider> getPartners() {
        return partners;
    }

    public List<Bike> getBikes() { return bikes; }
}
