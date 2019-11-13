package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class DiscountPolicy implements PricingPolicy {
    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {

    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {

        return null;
    }
}
