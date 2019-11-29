package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerDetails {
    // attributes //
    private String firstName;
    private String surName;
    private String address;
    private String postCode;
    private String phoneNumber;
    private List<Integer> orderNumbersList;

    // constructors //
    public CustomerDetails(String firstName, String surName, String address, String postCode, String phoneNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.orderNumbersList = new ArrayList<>();
    }

    // methods //
    // assigns the order number to the information registered about the Customer
    public void addOrderNumber(Booking booking){
        if(booking.getIsPaid()){
            this.getOrderNumbersList().add(booking.getOrderNumber());
        }
        else{
            System.out.println("This customer has not paid yet");
        }
    }

    // override the equals method for this class to use in testing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CustomerDetails c = (CustomerDetails) o;

        return Objects.equals(firstName, c.firstName) &&
                Objects.equals(surName, c.surName) &&
                Objects.equals(address, c.address) &&
                Objects.equals(postCode, c.postCode) &&
                Objects.equals(phoneNumber, c.phoneNumber) &&
                Objects.equals(orderNumbersList, c.orderNumbersList);

    }

    // getters and setters
    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Integer> getOrderNumbersList() {
        return orderNumbersList;
    }
}
