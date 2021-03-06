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
        partner.getPartners().add(this);
    }

    // removes providers from each others partners list
    public void removePartner(Provider partner) {
        this.getPartners().remove(partner);
        partner.getPartners().remove(this);
    }

    public void recordReturn(int orderNumber) {
        // check all the bookings of this provider for a match
        for (int i = 0; i < this.getBookings().size(); i++) {
            if (this.getBookings().get(i).getOrderNumber() == orderNumber) {
                Booking booking = this.getBookings().get(i);

                // remove the dates of the booking returned from each bike and set bike location to
                // this provider's location
                for (Bike bike : booking.getOrder().getBikes()) {
                    bike.setBikeLocation(this.getShopLocation());
                    bike.getBookedDates().remove(booking.getOrder().getSelectedDates());
                }
                // finally, remove the booking from the system as we no longer need it
                // by this stage the bikes are successfully with a provider, and the provider has recorded the return.
                booking.getOrder().getProvider().getBookings().remove(i);
                return;
            }
        }

        // else check each partner's booking for a match
        for (Provider provider : this.getPartners()) {
            for (int i = 0; i < provider.getBookings().size(); i++) {
                if (provider.getBookings().get(i).getOrderNumber() == orderNumber) {
                    Booking booking = provider.getBookings().get(i);
                    // notify the original provider that the bikes have been returned
                    notifyProvider(booking);
                    // for each bike in booking, set bike location to be the same as the current provider's location for
                    // deliverable to know where the bike is going
                    for (Bike bike : booking.getOrder().getBikes()) {
                        bike.setBikeLocation(this.getShopLocation());
                    }

                    // schedule a delivery to the original provider
                    DeliveryServiceFactory.getDeliveryService().scheduleDelivery(new DeliverableImpl(booking),
                            this.getShopLocation(), booking.getOrder().getProvider().getShopLocation(),
                            booking.getOrder().getSelectedDates().getEnd());

                    // no need to remove booking and dates as original provider will do that when they record the return
                    return;
                }
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
