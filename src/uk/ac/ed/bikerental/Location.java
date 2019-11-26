package uk.ac.ed.bikerental;

public class Location {
    private String postcode;
    private String address;
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    //Assuming that a location is near to another if the first two digits are equal
    public boolean isNearTo(Location other) {
       String postcodeFirstTwoDigits = postcode.substring(2,4);
       String otherPostcodeFirstTwoDigits = other.getPostcode().substring(2,4);
       return postcodeFirstTwoDigits.equals(otherPostcodeFirstTwoDigits);
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
