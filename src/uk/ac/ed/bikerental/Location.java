package uk.ac.ed.bikerental;

public class Location {
    private String postcode;
    private String address;
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    public boolean isNearTo(Location other) {
        int startingDigitIndex= 0;
       for (int i =0; i< postcode.length(); i++){
           if ( postcode.substring(i,i+1).contains("0123456789") ){
               startingDigitIndex = i;
               break;
           }
       }
       String postcodeFirstTwoDigits = postcode.substring(startingDigitIndex, startingDigitIndex +2);
       String otherPostcodeFirstTwoDigits = other.getPostcode().substring(startingDigitIndex, startingDigitIndex +2);
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
