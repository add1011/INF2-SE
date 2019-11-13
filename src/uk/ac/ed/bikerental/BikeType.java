package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeType {
    private String typeName;
    private BigDecimal replacementValue;

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