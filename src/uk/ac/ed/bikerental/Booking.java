package uk.ac.ed.bikerental;

public class Booking {
    private int orderNumber;
    private collectionMethod pickupMethod;
    private String bikesStatus;
    private Boolean isPaid;
    private Quote order;

    public Booking(int orderNumber, collectionMethod pickupMethod, String bikesStatus, Boolean isPaid, Quote order) {
        this.orderNumber = orderNumber;
        this.pickupMethod = pickupMethod;
        this.bikesStatus = bikesStatus;
        this.isPaid = isPaid;
        this.order = order;
    }

    public void checkout() {
        System.out.println("Customer checking out and paying.");
        this.isPaid = true;
    }

    public void sendBookingInfo() {
        System.out.println("Booking Information send to Customer");
    }

    // Getters and Setters
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

    public String getBikesStatus() {
        return bikesStatus;
    }

    public void setBikesStatus(String bikesStatus) {
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
