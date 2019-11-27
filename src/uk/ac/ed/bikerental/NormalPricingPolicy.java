package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NormalPricingPolicy implements PricingPolicy{
    // attributes //
    private Map<BikeType, BigDecimal> dailyRentalPrice;

    // constructors //
    public NormalPricingPolicy() { this.dailyRentalPrice = new HashMap<>(); }

    // methods //
    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration){
        BigDecimal totalPrice = new BigDecimal(0);
        for (Bike bike: bikes){
            if(dailyRentalPrice.containsKey(bike.getType() ) ) {
                BigDecimal bikeTypeDailyRentalPrice = dailyRentalPrice.get(bike.getType());
                totalPrice = totalPrice.add(bikeTypeDailyRentalPrice);
            }
        }
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        return totalPrice;
    }
    // getters and setters //
    public Map<BikeType, BigDecimal> getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        this.dailyRentalPrice.put(bikeType, dailyPrice);
    }


}
