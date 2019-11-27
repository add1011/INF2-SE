package uk.ac.ed.bikerental;

import java.util.Objects;

public class Booking {
    private Integer orderNumber;
    private collectionMethod pickupMethod;
    private Quote order;
    private CustomerDetails customer;
    private bikeStatuses bikesStatus;
    private Boolean isPaid;

    // constructors //
    public Booking(Integer orderNumber, collectionMethod pickupMethod, Quote order, CustomerDetails customer) {
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
        this.customer.getOrderNumbersList().add(this.orderNumber);
    }

    public void sendBookingInfo() {
        if(isPaid){
            System.out.println("Booking Information sent to "+ customer.getFirstName()+"'s phone number: "
                               + this.getCustomer().getPhoneNumber());
            System.out.println("Total Rental Price: " + order.getTotalPrice());
            System.out.println("Total Deposit: "+ order.getTotalDeposit());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Booking b = (Booking) o;

        return Objects.equals(orderNumber, b.orderNumber) &&
                Objects.equals(pickupMethod, b.pickupMethod) &&
                Objects.equals(order, b.order) &&
                Objects.equals(customer, b.customer) &&
                Objects.equals(bikesStatus, b.bikesStatus) &&
                Objects.equals(isPaid, b.isPaid);

    }

    // Getters and Setters //
    public CustomerDetails getCustomer() {
        return customer;
    }

    public Integer getOrderNumber() {
        return orderNumber;
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
