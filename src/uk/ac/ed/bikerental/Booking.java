package uk.ac.ed.bikerental;

public class Booking {
    private Customer customer;
    private int orderNumber;
    private collectionMethod pickupMethod;
    private bikeStatuses bikesStatus;
    private Boolean isPaid;
    private Quote order;

    // constructors //
    public Booking(int orderNumber, collectionMethod pickupMethod, Quote order) {
        this.orderNumber = orderNumber;
        this.pickupMethod = pickupMethod;
        this.bikesStatus = bikeStatuses.withProvider;
        this.isPaid = false;
        this.order = order;
    }

    // methods //
    public void checkout() {
        System.out.println("Customer checking out and paying.");
        this.isPaid = true;
    }

    public void sendBookingInfo() {
        System.out.println("Booking Information sent to Customer's phone number: " + this.getCustomer().getPhoneNumber());
    }

    // Getters and Setters //
    public Customer getCustomer() {
        return customer;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public collectionMethod getPickupMethod() {
        return pickupMethod;
    }

    public void setPickupMethod(collectionMethod pickupMethod) {
        this.pickupMethod = pickupMethod;
    }

    public bikeStatuses getBikesStatus() {
        return bikesStatus;
    }

    public void setBikesStatus(bikeStatuses bikesStatus) {
        this.bikesStatus = bikesStatus;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Quote getOrder() {
        return order;
    }

    public void setOrder(Quote order) {
        this.order = order;
    }
}
