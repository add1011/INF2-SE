package uk.ac.ed.bikerental;

import java.util.Objects;

public class DeliverableImpl implements Deliverable{
    private Booking booking;
    public DeliverableImpl(Booking currentBooking){
        this.booking = currentBooking;
    }

    @Override
    public void onPickup() {
        this.booking.setBikesStatus(bikeStatuses.inTransit);
    }

    @Override
    public void onDropoff() {
        this.booking.setBikesStatus(bikeStatuses.withCustomer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeliverableImpl d = (DeliverableImpl) o;

        return Objects.equals(booking, d.booking);
    }
}
