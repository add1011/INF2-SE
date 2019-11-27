package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NormalPolicy implements PricingPolicy{

        private Map<BikeType, BigDecimal> dailyRentalPrice;

        public NormalPolicy() {
            this.dailyRentalPrice = new HashMap<>();
        }

    // getters and setters
    public Map<BikeType, BigDecimal> getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        this.dailyRentalPrice.put(bikeType, dailyPrice);
    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration){
        BigDecimal totalPrice = new BigDecimal(0);
        for (Bike bike: bikes){
            if(dailyRentalPrice.containsKey(bike.getType() ) ) {
                BigDecimal bikeTypeDailyRentalPrice = dailyRentalPrice.get(bike.getType());
                totalPrice = totalPrice.add(bikeTypeDailyRentalPrice);
            }
        }
        return totalPrice;
    }
}
