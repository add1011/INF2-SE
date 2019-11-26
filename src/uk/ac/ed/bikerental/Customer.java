package uk.ac.ed.bikerental;

import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String surName;
    private String address;
    private String postCode;
    private int phoneNumber;
    private ArrayList<Integer> orderNumbersList;

    public Customer(String firstName, String surName, String address, String postCode, int phoneNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
    }

    //Assigns the order number to the information registered about the Customer
    public void addOrderNumber(Booking booking){
        if(booking.getIsPaid()){
            orderNumbersList.add(booking.getOrderNumber());
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
