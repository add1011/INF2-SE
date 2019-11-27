package uk.ac.ed.bikerental;

import java.util.List;

public class CustomerDetails {
    private String firstName;
    private String surName;
    private String address;
    private String postCode;
    private String phoneNumber;
    private List<Integer> orderNumbersList;

    public CustomerDetails(String firstName, String surName, String address, String postCode, String phoneNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
    }

    //Assigns the order number to the information registered about the Customer
    public void addOrderNumber(Booking booking){
        if(booking.getIsPaid()){
            this.getOrderNumbersList().add(booking.getOrderNumber());
        }
        else{
            System.out.println("This customer has not paid yet");
        }
    }
    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getOrderNumbersList() {
        return orderNumbersList;
    }

    public void setOrderNumbersList(List<Integer> orderNumbersList) {
        this.orderNumbersList = orderNumbersList;
    }
}
