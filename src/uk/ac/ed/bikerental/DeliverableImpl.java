package uk.ac.ed.bikerental;

import java.util.Iterator;
import java.util.Objects;

public class DeliverableImpl implements Deliverable {
    // attributes //
    /* use Booking as the identifier for the deliverable, on pickup or drop off the
     * delivery driver will be able to select from a list which bookings he is dealing with
     */
    private Booking booking;

    // constructors //
    public DeliverableImpl(Booking currentBooking) {
        this.booking = currentBooking;
    }

    // methods //
    // when called, changes the status of the booking of the deliverable being picked up
    @Override
    public void onPickup() {
        this.booking.setBikesStatus(bikeStatuses.inTransit);
    }

    // when called, changed the status of the booking of the deliverable being dropped off
    //Depending on where the bikeLocation is, it will be set to be Withcustomer or to be Withprovider
    @Override
    public void onDropoff() {
        Iterator bikesBookingIterator = booking.getOrder().getBikes().iterator();
        boolean isStillWithProvider = false;

        while (bikesBookingIterator.hasNext()) {
            if (bikesBookingIterator.next().equals(booking.getOrder().getProvider().getShopLocation()))
                isStillWithProvider = true;
        }
        //if all bikes in booking are still with provider
        if (isStillWithProvider) {
            //if Collection method is pickup by customer themselves,
            //then we will change the bikes' locations to be the same as
            //the location provided by customer
            //Note: if collectionMethod is delivery, then scheduleDelivery and other functions
            //in DeliveryServiceFactor and other relevant classes takes care of setting bikes' locations
            if (booking.getPickupMethod() == collectionMethod.PickUp) {
                while (bikesBookingIterator.hasNext()) {
                    Bike bike = (Bike) bikesBookingIterator.next();
                    //We will be assuming that if the bike location is not providers and happens to
                    //be similar to customer's location, then bike must be with customer during that period
                    bike.setBikeLocation(new Location(
                                    booking.getCustomer().getPostCode(),
                                    booking.getCustomer().getAddress()
                            )
                    );
                }
            }
            this.booking.setBikesStatus(bikeStatuses.withCustomer);
        } else {
            this.booking.setBikesStatus(bikeStatuses.withProvider);
        }
    }

    // override the equals method for this class to use in testing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeliverableImpl d = (DeliverableImpl) o;

        return Objects.equals(booking, d.booking);
    }
}
