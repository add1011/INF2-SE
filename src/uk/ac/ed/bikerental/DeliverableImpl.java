package uk.ac.ed.bikerental;

import java.util.Objects;

public class DeliverableImpl implements Deliverable{
    // attributes //
    /* use Booking as the identifier for the deliverable, on pickup or drop off the
    * delivery driver will be able to select from a list which bookings he is dealing with
     */
    private Booking booking;

    // constructors //
    public DeliverableImpl(Booking currentBooking){
        this.booking = currentBooking;
    }

    // methods //
    // when called, changes the status of the booking of the deliverable being picked up
    @Override
    public void onPickup() {
        this.booking.setBikesStatus(bikeStatuses.inTransit);
    }

    // when called, changed the status of the booking of the deliverable being dropped off
    @Override
    public void onDropoff() {
        this.booking.setBikesStatus(bikeStatuses.withCustomer);
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
