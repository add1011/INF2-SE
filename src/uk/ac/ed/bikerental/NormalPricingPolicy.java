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
    
    //Returns the total daily price by adding up all the rental prices.
    //Note that DateRange duration is unimportant here as it does not influence the total daily rental pricing for normal
    //policies.
    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration){
        BigDecimal totalDailyPrice = new BigDecimal(0);
        for (Bike bike: bikes){
            if(dailyRentalPrice.containsKey(bike.getType() ) ) {
                BigDecimal bikeTypeDailyRentalPrice = dailyRentalPrice.get(bike.getType());
                totalDailyPrice = totalDailyPrice.add(bikeTypeDailyRentalPrice);
            } else {
                throw new IllegalArgumentException("Requested bikeType not found in the system!");
            }
        }
        totalDailyPrice = totalDailyPrice.setScale(2, RoundingMode.HALF_UP);
        return totalDailyPrice;
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
