package ru.rustavil.fuel_consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class FuelConsumption {

    @Builder.Default
    private final UUID id = UUID.randomUUID();
    private final Driver driver;
    private final FuelType fuelType;
    private final Double fuelVolume;
    private final BigDecimal fuelPrice;

    public static FuelConsumptionBuilder builder() {
        return new ValidateFuelConsumptionBuilder();
    }

    private static class ValidateFuelConsumptionBuilder extends FuelConsumptionBuilder {

        @Override
        public FuelConsumption build() {
            if (Objects.isNull(super.driver)) {
                throw new InvalidException("Driver required");
            }
            if (super.fuelVolume <= 0) {
                throw new InvalidException("Fuel volume must be great than 0");
            }
            if (super.fuelPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidException("Fuel price must be great than 0");
            }
            return super.build();
        }

    }
}
