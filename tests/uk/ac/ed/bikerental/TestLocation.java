package uk.ac.ed.bikerental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestLocation {
    private Location location1, location2, location3;

    @BeforeEach
    void setUp() throws Exception {
        location1 = new Location("EH28AA", "Any Address");
        location2 = new Location("EH28BB", "Any address");
        location3 = new Location("GO20AA", "Any address");
    }

    //Check that locations with the same two first digits returns true, in which ever order it is presented in
    @Test
    void testNearToSuccess() {
        assertTrue(location1.isNearTo(location2));
        assertTrue(location2.isNearTo(location1));
    }

    //Check Fail condition: The function returned must be false if the two locations are not near each other
    @Test
    void testNearToFail() {
        assertFalse(location1.isNearTo(location3));
        assertFalse(location2.isNearTo(location3));
    }

    //ifPostCode length > 6 , we will have an AssertionError thrown
    //This tests check if that happens
    @Test
    void testPostCodeLengthThrowsError() {
        assertThrows(AssertionError.class, () -> {
            Location location = new Location("EH00OHNOTOOBIG", "Any Address");
        });
    }


}
