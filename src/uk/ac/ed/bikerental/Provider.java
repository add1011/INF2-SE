package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Provider {
    // attributes //
    private List<Booking> bookings = new ArrayList<>();
    private List<Bike> bikes = new ArrayList<>();
    private PricingPolicy pricingPolicy;
    private String providerName;
    private Location shopLocation;
    private BigDecimal depositRate;
    private List<Provider> partners;

    // constructors //
    public Provider(String providerName, Location shopLocation, BigDecimal depositRate, PricingPolicy policy) {
        this.providerName = providerName;
        this.shopLocation = shopLocation;
        this.depositRate = depositRate;
        this.partners = new ArrayList<>();
        this.pricingPolicy = policy;
    }

    // methods //
    // creates a new Bike object and adds it to bikes
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

    // removes providers from each others partners list
    public void removePartner(Provider partner) {
        this.getPartners().remove(partner);
        partner.getPartners().remove(this);
    }

    public void recordReturn(int orderNumber) {
        for (int i = 0; i < this.getBookings().size(); i++) {
            //Check if we have an orderNumber == one of the orderNumbers in the list of bookings stored with the Provider
            if (this.getBookings().get(i).getOrderNumber() == orderNumber) {
                Booking booking = this.getBookings().get(i);
                //If the providerLocation is not the same as this shop's location, this means that this shop's location
                //is that of a partner. Hence, we will notify Original Provider of the booking
                // and we'll schedule a deliveryService
                if (!(booking.getOrder().getProvider().getShopLocation().equals(this.getShopLocation()))) {
                    notifyProvider(booking);
                    DeliveryServiceFactory.getDeliveryService().scheduleDelivery(new DeliverableImpl(booking),
                            this.getShopLocation(), booking.getOrder().getProvider().getShopLocation(),
                            booking.getOrder().getSelectedDates().getEnd());
                }
                //For each bike in booking, remove dates from booking and set bike location
                //to be the same as the provider's location
                for (Bike bike : booking.getOrder().getBikes()) {
                    bike.getBookedDates().remove(booking.getOrder().getSelectedDates());
                    bike.setBikeLocation(this.getShopLocation());
                }
                //Finally remove the booking from the system as we no longer need it
                //By this stage the bikes are successfully with the provider, and the provider has recorded the return.
                booking.getOrder().getProvider().getBookings().remove(i);
                break;
            }
        }
    }

    // would in practice send an actual message to the original provider
    private void notifyProvider(Booking booking) {
        System.out.println(booking.getOrder().getProvider().getProviderName()
                + " has been notified of the bike return.");
    }

    // getters and setters //
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public PricingPolicy getPricingPolicy() {
        return pricingPolicy;
    }

    // not used but kept in case provider would want to change their pricing policy
    public void setPricingPolicy(PricingPolicy pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
    }

    public String getProviderName() {
        return providerName;
    }

    public Location getShopLocation() {
        return shopLocation;
    }

    public BigDecimal getDepositRate() {
        return depositRate;
    }

    // not used but kept in case provider would want to change their deposit rate
    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public List<Provider> getPartners() {
        return partners;
    }

    public List<Bike> getBikes() {
        return bikes;
    }
}
