package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiscountPolicy implements PricingPolicy {
    private Map<BikeType, BigDecimal> dailyRentalPrice;

    public DiscountPolicy() {
        this.dailyRentalPrice = new HashMap<>();
    }

    public Map<BikeType, BigDecimal> getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        this.dailyRentalPrice.put(bikeType, dailyPrice);
    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Bike bike : bikes) {
            BigDecimal bikeTypeDailyRentalPrice = dailyRentalPrice.get(bike.getType());
            totalPrice = totalPrice.add(bikeTypeDailyRentalPrice);
        }

        LocalDate start = duration.getStart();
        LocalDate end = duration.getEnd();

        int startDay = start.getDayOfYear();
        int endDay = end.getDayOfYear();

        int rentalLength;
        if (startDay > endDay) {
            rentalLength = endDay - startDay + 365;
        } else {
            rentalLength = endDay - startDay;
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

        return totalPrice;
    }
}
