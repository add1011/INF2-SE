package uk.ac.ed.bikerental;


import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;

public class Location {
    private String postcode;
    private String address;

    /**
     * Constructs a new Location object to reflect a duration by specifying
     * a start and an end date in the constructor call
     * @param postcode String value that specifies the postcode of the location. Postcode
     * @param address String value that specifies the street address of the location
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }


    /**
     *Check that the two locations are near each other if the first two digits of the two postcodes
     * including the characters before them are equal
     * @param other the location to check the stated condition
     * @return True if the first two digits from the postcodes are equal. Return False otherwise
     */
    public boolean isNearTo(Location other) {
        int indexOfFirstDigit = 0;
        for(int i =0 ; i< postcode.length() ; i++){
           Character currentCharacter = postcode.substring(i,i+1).charAt(0);
            if (Character.isDigit(currentCharacter) ) {
                indexOfFirstDigit = i;
                break;
            }
        }

       String postcodeFirstTwoDigits = postcode.substring(0, indexOfFirstDigit+2);
       String otherPostcodeFirstTwoDigits = other.getPostcode().substring(0, indexOfFirstDigit+2);
       return postcodeFirstTwoDigits.equals(otherPostcodeFirstTwoDigits);
    }

    /**
     *Getter method that returns the postcode of current Location object
     * @return Returns postcode
     */
    public String getPostcode() {
        return postcode;
    }
    /**
     *Getter method that returns the address of current Location object
     * @return Returns address
     */
    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
