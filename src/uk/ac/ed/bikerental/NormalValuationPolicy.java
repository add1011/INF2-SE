package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

//Behaviour is unimportant, hence calculatevalue returns the depositAmount already set
public class NormalValuationPolicy implements ValuationPolicy {
    // attributes //
    private BigDecimal depositAmount;

    // methods //
    public BigDecimal calculateValue(Bike bike, LocalDate date) { return this.depositAmount; }

    // getters and setters //
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
}