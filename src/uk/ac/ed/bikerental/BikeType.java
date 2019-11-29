package uk.ac.ed.bikerental;

import java.math.BigDecimal;

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
    public String getTypeName() {
        return typeName;
    }

    public BigDecimal getReplacementValue() {
        return replacementValue;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }


}