package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

//Behaviour is unimportant, hence calculatevalue returns the depositAmount already set
//this is simply a mock
public class NormalValuationPolicy implements ValuationPolicy {
    // attributes //
    private BigDecimal depositAmount;

    // methods //
    // simply returns the depositAmount of the Bike that is already calculated as a valuation is not calculated
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        return this.depositAmount;
    }

    // getters and setters //
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
}