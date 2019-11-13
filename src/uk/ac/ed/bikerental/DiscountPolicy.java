package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiscountPolicy implements PricingPolicy {
    private Map<BikeType, BigDecimal> dailyRentalPrice;

    public DiscountPolicy() {
        this.dailyRentalPrice = new HashMap<>();
    }

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        this.dailyRentalPrice.put(bikeType, dailyPrice);
    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Bike bike : bikes) {
            BigDecimal bikeTypeDailyRentalPrice = dailyRentalPrice.get(bike.getType());
            totalPrice.add(bikeTypeDailyRentalPrice);
        }

        
        return null;
    }
}
