package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

//Behaviour is unimportant, hence calculatevalue returns a random number
public class MockValuationPolicy implements ValuationPolicy {

    // 900-3*0.1*900
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        return new BigDecimal(630);
    }


}