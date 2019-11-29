package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiscountPolicy implements PricingPolicy {
    // attributes //
    private Map<BikeType, BigDecimal> dailyRentalPrice;

    // constructors //
    public DiscountPolicy() {
        this.dailyRentalPrice = new HashMap<>();
    }

    // methods //
    // finds the discounted price of the given collection of bikes for the rental duration
    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        // create the output variable and sum the dailyRentalPrices of each bike in the given collection
        BigDecimal totalDailyPrice = new BigDecimal(0);
        for (Bike bike : bikes) {
            // if the bikeType of the current bike is not in dailyRentalPrice throw an error
            if (dailyRentalPrice.containsKey(bike.getType())) {
                BigDecimal bikeTypeDailyRentalPrice = dailyRentalPrice.get(bike.getType());
                totalDailyPrice = totalDailyPrice.add(bikeTypeDailyRentalPrice);
            } else {
                throw new IllegalArgumentException(bike.getType() + " does not have a daily rental price set!");
            }
        }

        // use the toDays() method of DateRange to find the rental length
        int rentalLength = (int) duration.toDays();

        // if the rental length is less than a day long throw an exception
        if (rentalLength < 1) {
            throw new IllegalArgumentException("You can only rent a bike forward in time...");
        }

        // for each discount tier apply the appropriate discount to the total price
        if (rentalLength > 0 && rentalLength <= 2) {
            totalDailyPrice = totalDailyPrice.multiply(new BigDecimal(1));
        } else if (rentalLength > 2 && rentalLength <= 6) {
            totalDailyPrice = totalDailyPrice.multiply(new BigDecimal(0.95));
        } else if (rentalLength > 6 && rentalLength <= 13) {
            totalDailyPrice = totalDailyPrice.multiply(new BigDecimal(0.9));
        } else if (rentalLength > 13) {
            totalDailyPrice = totalDailyPrice.multiply(new BigDecimal(0.85));
        }
        // round the total price to 2 decimal places
        totalDailyPrice = totalDailyPrice.setScale(2, RoundingMode.HALF_UP);
        return totalDailyPrice;
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
