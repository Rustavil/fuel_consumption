package ru.rustavil.fuel_consumption.domain;

import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.util.Map;

@Builder
public class FuelPurchaseNotification implements Notification {

    private static final String TITLE = "New fuel purchase!";
    private static final String MSG_TMPL = "Total fuel volume: %.2fL (%s). Total price: %.2fEUR";

    private final Double totalFuelVolume;
    private final Map<FuelType, Double> fuelVolumeMap;
    private final Double totalPrice;

    public FuelPurchaseNotification(Double totalFuelVolume,
                                    Map<FuelType, Double> fuelVolumeMap,
                                    Double totalPrice) {
        if (totalFuelVolume < 0) {
            throw new InvalidException("Total fuel price must not be negative");
        }
        this.totalFuelVolume = totalFuelVolume;
        this.fuelVolumeMap = fuelVolumeMap;
        if (totalPrice < 0) {
            throw new InvalidException("Total fuel price must not be negative");
        }
        this.totalPrice = totalPrice;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String buildMsg() {
        return String.format(MSG_TMPL, totalFuelVolume, Joiner.on(",").withKeyValueSeparator(":").join(this.fuelVolumeMap), totalPrice);
    }

    public static FuelPurchaseNotificationBuilder builder() {
        return new ValidateFuelPurchaseNotificationBuilder();
    }

    private static class ValidateFuelPurchaseNotificationBuilder extends FuelPurchaseNotificationBuilder {

        @Override
        public FuelPurchaseNotification build() {
            if (super.totalFuelVolume < 0) {
                throw new InvalidException("Total fuel price must not be negative");
            }
            if (super.totalPrice < 0) {
                throw new InvalidException("Total fuel price must not be negative");
            }
            return super.build();
        }

    }
}
