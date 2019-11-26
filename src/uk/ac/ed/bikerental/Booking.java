package uk.ac.ed.bikerental;

public class Booking {
    private Customer customer;
    private int orderNumber;
    private collectionMethod pickupMethod;
    private bikeStatuses bikesStatus;
    private Boolean isPaid;
    private Quote order;

    // constructors //
    public Booking(int orderNumber, collectionMethod pickupMethod, Quote order, Customer customer) {
        this.orderNumber = orderNumber;
        this.pickupMethod = pickupMethod;
        this.bikesStatus = bikeStatuses.withProvider;
        this.isPaid = false;
        this.order = order;
        this.customer = customer;
    }

    // methods //
    public void checkout() {
        System.out.println("Customer checking out and paying.");
        this.isPaid = true;

        //Assigns the order number to the information registered about the Customer
        customer.addOrderNumber(this);
    }



    public void sendBookingInfo() {
        if(isPaid){
            System.out.println("Booking Information sent to "+ customer.getFirstName()+"'s phone number: " + this.getCustomer().getPhoneNumber());
        }
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

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean paid) {
        isPaid = paid;
    }

    public Quote getOrder() {
        return order;
    }

    public void setOrder(Quote order) {
        this.order = order;
    }
}
