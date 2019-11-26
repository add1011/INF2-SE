package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class DiscountPolicy implements PricingPolicy {
    private Map<BikeType, BigDecimal> dailyRentalPrice;

    public DiscountPolicy() {
        this.dailyRentalPrice = new HashMap<>();
    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Bike bike : bikes) {
            if(dailyRentalPrice.containsKey(bike.getType() ) ) {
                BigDecimal bikeTypeDailyRentalPrice = dailyRentalPrice.get(bike.getType());
                totalPrice = totalPrice.add(bikeTypeDailyRentalPrice);
            }
        }

        int rentalLength = (int) DAYS.between(duration.getStart(), duration.getEnd());

        if (rentalLength < 1 ) {
            throw new IllegalArgumentException("You can only rent a bike forward in time...");
        }

        if (rentalLength > 0 && rentalLength <= 2) {
            totalPrice = totalPrice.multiply(new BigDecimal(1));
        } else if (rentalLength > 2 && rentalLength <= 6) {
            totalPrice = totalPrice.multiply(new BigDecimal(0.95));
        } else if (rentalLength > 6 && rentalLength <= 13) {
            totalPrice = totalPrice.multiply(new BigDecimal(0.9));
        } else if (rentalLength > 13) {
            totalPrice = totalPrice.multiply(new BigDecimal(0.85));
        }
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        return totalPrice;
    }

    // getters and setters
    public Map<BikeType, BigDecimal> getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        this.dailyRentalPrice.put(bikeType, dailyPrice);
    }
}
