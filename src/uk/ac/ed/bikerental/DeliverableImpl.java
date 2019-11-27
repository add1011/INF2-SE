package uk.ac.ed.bikerental;

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
}
