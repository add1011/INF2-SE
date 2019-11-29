package uk.ac.ed.bikerental;


public class Location {
    private String postcode;
    private String address;

    /**
     * Constructs a new Location object to reflect a duration by specifying
     * a start and an end date in the constructor call
     *
     * @param postcode String value that specifies the postcode of the location. Postcode must be of length<=6
     * @param address  String value that specifies the street address of the location
     */
    public Location(String postcode, String address) {
        assert postcode.length() <= 6;
        this.postcode = postcode;
        this.address = address;
    }

    /**
     * Check that the two locations are near each other if the first two characters of the two postcode are equal
     *
     * @param other the location to be compared with
     * @return True if the first two characters from the postcodes are equal. Return False otherwise
     */
    public boolean isNearTo(Location other) {
        return postcode.substring(0, 2).equals(other.getPostcode().substring(0, 2));
    }

    /**
     * Getter method that returns the postcode of current Location object
     *
     * @return Returns postcode
     */
    public String getPostcode() {
        return postcode;
    }


    /**
     * Getter method that returns the address of current Location object
     *
     * @return Returns address
     */
    public String getAddress() {
        return address;
    }

    // You can add your own methods here
}
