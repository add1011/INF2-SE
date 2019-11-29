package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    // attributes //
    private String typeName;
    private BigDecimal replacementValue;

    // constructors //
    public BikeType(String typeName, BigDecimal replacementValue){
        this.typeName= typeName;
        this.replacementValue = replacementValue;
    }

    // getters and setters //
    public BigDecimal getReplacementValue() {
        return replacementValue;
    }

    // not used but kept in case the replacement value needs to be changed
    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }


}